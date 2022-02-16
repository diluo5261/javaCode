package cn.itcast.order.command;


import cn.itcast.order.feign.UserFeignClient;
import cn.itcast.order.pojo.User;
import com.netflix.hystrix.*;

public class OrderCommand extends HystrixCommand<User> {

    private UserFeignClient userFeignClient;

    private Long id;

    public OrderCommand(UserFeignClient userFeignClient, Long id) {
        super(setter());
        this.userFeignClient = userFeignClient;
        this.id = id;
    }

    private static Setter setter() {

        // 服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("orderservice");
        // 服务标识
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("order");
        // 线程池名称
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("orderservice_pool");
        /**
         * 线程池配置
         *     withCoreSize :  线程池大小为10
         *     withKeepAliveTimeMinutes:  线程存活时间15秒
         *     withQueueSizeRejectionThreshold  :队列等待的阈值为100,超过100执行拒绝策略
         */
        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter().withCoreSize(50)
                .withKeepAliveTimeMinutes(15).withQueueSizeRejectionThreshold(100);

        // 命令属性配置Hystrix 开启超时
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                // 采用线程池方式实现服务隔离
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                // 禁止
                .withExecutionTimeoutEnabled(false);
        return HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey).andThreadPoolKey(threadPoolKey)
                .andThreadPoolPropertiesDefaults(threadPoolProperties).andCommandPropertiesDefaults(commandProperties);

    }

    @Override
    protected User run() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return userFeignClient.findById(id);
    }

    @Override
    protected User getFallback(){
        System.out.println(Thread.currentThread().getName());
    return null;
    }
}

