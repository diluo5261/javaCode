package cn.itcast.order.feign;



import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
    1.声明需要调用的微服务名称
    @FeignClient
        name : 服务提供者的名称
 */

@FeignClient(name = "userservice")
public interface UserFeignClient {

    /*
        2.配置需要调用的服务接口
     */

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}
