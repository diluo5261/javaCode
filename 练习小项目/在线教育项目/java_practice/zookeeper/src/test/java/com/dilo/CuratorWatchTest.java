package com.dilo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CuratorWatchTest {
    CuratorFramework client = null;


    @Before
    public void testConnect(){
        /*

        String connectString : 连接字符串,zk server地址和端口,192.168.169.128,如果是集群,可以用,隔开
        int sessionTimeoutMs, : 会话超时时间 单位 ms
        int connectionTimeoutMs, : 连接超时时间 ms
        RetryPolicy retryPolicy  : 重试策略
         */

        //重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,10);
        //第一种方式
        client = CuratorFrameworkFactory.newClient("192.168.169.128:2181",
                60 * 1000, 15 * 1000, retryPolicy);



        //第二种方式
        CuratorFrameworkFactory.builder().connectString("192.168.169.128:2181")
                .sessionTimeoutMs(60*1000)
                .connectionTimeoutMs(60*1000)
                .retryPolicy(retryPolicy).namespace("dilo").build();

        //开启连接
        client.start();
    }

    /**
     * 演示 NodeCache:给指定一个节点注册监听
     */
   @Test
   public void testNodeCache() throws Exception {
       //1.创建NodeCache对象
       NodeCache nodeCache = new NodeCache(client,"app1");

       //2.注册监听
       nodeCache.getListenable().addListener(new NodeCacheListener() {
           @Override
           public void nodeChanged() throws Exception {
               System.out.println("节点变化了");

               //获取修改节点后的数据
               byte[] data = nodeCache.getCurrentData().getData();
               System.out.println(data.toString());
           }
       });

       //3.开启监听
       //如果设置为true,则开启监听,加载缓冲数据
       nodeCache.start(true);

       while(true){

       }
   }

    /**
     * 演示 PathChildrenCache :监听某个节点的所有子节点
     */
   @Test
   public void testPathChildrenCache() throws Exception {
       //1.创建监听对象
       PathChildrenCache cache = new PathChildrenCache(client,"/app2",true);

       //2.绑定监听器
       cache.getListenable().addListener(new PathChildrenCacheListener() {
           @Override
           public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {

               //监听子节点的变更,并且拿到变更后的数据
               //1.获取类型
               PathChildrenCacheEvent.Type type = pathChildrenCacheEvent.getType();
               //2.判断类型是否式  update

               if (type.equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)){
                   byte[] data = pathChildrenCacheEvent.getData().getData();
                   System.out.println(new String(data));
               }
           }
       });

       //3.开启
       cache.start();
   }




    @After
    public void close(){
        if (client != null){
            client.close();
        }
    }

}
