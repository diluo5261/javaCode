package com.dilo.gmall.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.dilo.gmall.common.constant.RedisConst;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.common.util.IpUtil;
import com.dilo.gmall.model.user.UserInfo;
import com.dilo.gmall.user.service.UserService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/user/passport")
public class PassportApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    //其那台页面传递是json串
    @PostMapping("/login")
    public Result longin(@RequestBody UserInfo userInfo, HttpServletRequest request, HttpResponse response){
        //进入控制器
        UserInfo loginInfo = userService.login(userInfo);


        if (loginInfo != null){

            //登录成功,生成token,同时将nickName一起放入到map中,后台将数据返回给页面,页面通过js,将tocken放入到cookie中
            String token = UUID.randomUUID().toString();
            HashMap<String, Object> map = new HashMap<>();
            map.put("token",token);
            map.put("nickName",loginInfo.getNickName());

            //判断用具是否登录的真实依据,应该是将数据放入缓存
            //确定缓存
            //登录成功需要生成token,页面需要获取!,然后将token放入cookie中!还需要放入userInfo对象
            //为了防止别人伪造token,需要再存储一个IP
            //userKey=user:login:userinfo value={"ip":"1982.168.169.169","userId":"2"}
            String userKey = RedisConst.USER_LOGIN_KEY_PREFIX+token;
            String ip = IpUtil.getIpAddress(request);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId",loginInfo.getId().toString());
            jsonObject.put("ip",ip);

            //将用户id和ip放入到缓存
            redisTemplate.opsForValue().set(userKey,jsonObject.toJSONString(),RedisConst.USERKEY_TIMEOUT, TimeUnit.SECONDS);

            //将map中的数据返回给前台
            return Result.ok(map);

        }else{
            return Result.fail().message("用户名或密码错误");
        }
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        //登陆的时候数据存储在 reids和cookie,删除对应的数据
        //缓存的key = user:login:UUID
        String userKey = RedisConst.USER_LOGIN_KEY_PREFIX+request.getHeader("token");
        redisTemplate.delete(userKey);
        return Result.ok();
    }


}
