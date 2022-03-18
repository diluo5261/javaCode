package com.dilo.gmall.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.common.result.ResultCodeEnum;
import com.dilo.gmall.common.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;

@Component
public class AuthGlobalFilter implements GlobalFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    //从配置文件中获取控制器都有谁
    @Value("${authUrls.url}")
    private String authUrls;


    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取用户访问的URL
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        //判断当前的path是哪一种,
        //1./**/inner/**属于内部接口,需要做出响应
        //判断是否包含该路径,需要用到正则表达式,引入AntPathMatcher类

        if (antPathMatcher.match("/**/inner/**",path)){
            //如果包含inner内部接口
            //获取到响应对象
            ServerHttpResponse response = exchange.getResponse();
            //不能继续走了,返回
            return out(response, ResultCodeEnum.PERMISSION);
        }

        //2.1获取用户ID,用户id如果不存在就是没有登录
        String userId = this.getUserId(request);
        String userTempId = this.getUserTempId(request);

        //调用了
        if ("-1".equals(userId)){
            ServerHttpResponse response = exchange.getResponse();
            return out(response,ResultCodeEnum.PERMISSION);
        }


        //2.判断用户是否访问了trade.html,myOrder.html 这样的控制器时，必须要登录！
        //  authUrlsUrl= trade.html,myOrder.html,list.html
        //  校验的规则：http://www.gmall.com/index.html 不需要  http://list.gmall.com/list.html?category3Id=61 需要！
        String[] split = authUrls.split(",");
        //循环遍历
        for (String url : split) {
            //2.2用户为登录,并且访问的控制器是需要登录的
            if (path.indexOf(url) != -1 && StringUtils.isEmpty(userId)){
                //需要跳转到登录页面
                ServerHttpResponse response = exchange.getResponse();
                //做一些设置
                //303状态码表示表示请求对应资源存在着另一个URL,应使用重定向获取资源
                response.setStatusCode(HttpStatus.SEE_OTHER);
                //  http://passport.gmall.com/login.html?originUrl=http://list.gmall.com/list.html?category3Id=61
                //  request.getURI() = http://list.gmall.com/list.html?category3Id=61
                response.getHeaders().set(HttpHeaders.LOCATION,"http://www.gmall.com/login.html?originUrl="+request.getURI());
                //重定向
                response.setComplete();
            }
        }


        //3.用户访问了  /api/**/auth/**  需要登录！
        if (antPathMatcher.match("/api/**/auth/**",path)){
            //判断当前用户是否登录
            if (StringUtils.isEmpty(userId)){
                //做出响应
                ServerHttpResponse response = exchange.getResponse();
                //不能继续走了
                return out(response,ResultCodeEnum.LOGIN_AUTH);
            }
        }

        //验证通过之后,将用户的id传递给后台微服务
        if (!StringUtils.isEmpty(userId) || !StringUtils.isEmpty(userTempId)){
            if (!StringUtils.isEmpty(userId)){
                //需要将用户id放入请求头中
                request.mutate().header("userId",userId).build();
            }else if (!StringUtils.isEmpty(userTempId)){
                request.mutate().header("userTempId",userTempId).build();
            }
            return chain.filter(exchange.mutate().request(request).build());
        }

        return chain.filter(exchange);
    }


    /**
     * 获取临时用户id
     * @param request
     * @return
     */
    private String getUserTempId(ServerHttpRequest request) {
        HttpCookie httpCookie = request.getCookies().getFirst("userTempId");
        String userTempId = "";
        if (httpCookie != null){

            userTempId  = httpCookie.getValue();
        }else{
            List<String> stringList = request.getHeaders().get("userTempId");
            if (stringList != null){
                userTempId = stringList.get(0);
            }
        }
        return userTempId;
    }


    /**
     * 获取用户id
     * @param request
     * @return
     */
    private String getUserId(ServerHttpRequest request) {
        //用户id存储在缓存中,需要获取到用户id,引入redisTemplate
        //获取到token,组装成key
        String token="";
        List<String> stringList = request.getHeaders().get("token");
        if (!CollectionUtils.isEmpty(stringList)){
            //从header中获取token
            token = stringList.get(0);
        }else{
            //从cookie中获取token
            HttpCookie token1 = request.getCookies().getFirst("token");
            if (token1 != null){
                token = token1.getValue();
            }
        }

        //组装成缓存的key
        String userKey ="user:login:"+token;

        //从缓存中获取userKey的value
        //  get userKey  "{\"ip\":\"192.168.200.1\",\"userId\":\"2\"}"
        String object = (String) redisTemplate.opsForValue().get(userKey);

        //判断key是否存在
        if (!StringUtils.isEmpty(object)){
            //本质存储的是JSONObject
            JSONObject jsonObject = JSON.parseObject(object, JSONObject.class);

            //校验,先获取当前客户端的IP
            String ip = (String) jsonObject.get("ip");
            String currIp = IpUtil.getGatwayIpAddress(request);

            if (currIp.equals(ip)){
                String userId = (String) jsonObject.get("userId");
                return userId;
            }else{
                return "-1";
            }

        }
        return "";

    }

    private Mono<Void> out(ServerHttpResponse response, ResultCodeEnum resultCodeEnum) {
        Result<Object> result = Result.build(null, resultCodeEnum);
        //需要将result输入到页面,将result编程json字符串
        String strJson = JSON.toJSONString(result);

        //想办法输出strJson
        DataBuffer dataBuffer = response.bufferFactory().wrap(strJson.getBytes());

        //使用响应对象,设置响应头
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        //dataBuffer 要输出出去
        return response.writeWith(Mono.just(dataBuffer));
    }
}
