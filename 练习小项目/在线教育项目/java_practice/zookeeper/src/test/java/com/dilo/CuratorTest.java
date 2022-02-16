package com.dilo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CuratorTest {
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

    /*
    创建节点:creat 持久 临时  顺序  数据
    1. 基本创建
    2.创建节点,带有数据
    3.设置节点的类型
    4.创建多级节点
     */

    @Test
    public void testCreat() throws Exception {
        //1.基本创建
        //如果创建节点,没有指定数据,则默认将当前客户端的 ip 作为数据存储
        String path = client.create().forPath("/app1");
        //2.创建节点,带有数据
        String path2 = client.create().forPath("/app2","hello".getBytes());
        System.out.println(path);

        //3.设置节点的类型,默认类型,持久化
        String path3 = client.create().withMode(CreateMode.EPHEMERAL).forPath("/app3");

        //4.创建多级节点,如果父节点不存在,就创建父节点
        String path4 = client.create().creatingParentContainersIfNeeded().forPath("/app4/p1");


    }

    /**
     * 查询节点:
     * 查询数据 : get
     * 查询子节点: ls
     * 查询节点状态信息 : ls -s
     */
    @Test
    public  void testGet() throws Exception {
        //1.查询数据 : get
        byte[] data = client.getData().forPath("app1");
        System.out.println(data.toString());

        //2.查询子节点
        List<String> list = client.getChildren().forPath("/app4");
        System.out.println(list);

        //3.查询节点的状态信息
        Stat status = new Stat();
        byte[] data1 = client.getData().storingStatIn(status).forPath("app1");

    }

    @Test
    public void testSet() throws Exception {
        //1.修改数据
        Stat stat = client.setData().forPath("/app1", "hello".getBytes());

        //2.根据版本号修改,多线程修改
        Stat stat1 = new Stat();
        client.getData().storingStatIn(stat1).forPath("/app1");
        int version = stat1.getVersion();

        client.setData().withVersion(version).forPath("/app1", "hello".getBytes());
    }

    @Test
    public void testDelete() throws Exception {
        //删除单个节点
        client.delete().forPath("app1");

        //2.删除带有子节点的节点
        client.delete().deletingChildrenIfNeeded().forPath("app4");

        //3.必须成功的删除
        client.delete().guaranteed().forPath("/app2");

        //4.回调
        client.delete().guaranteed().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("我被删除了");
                System.out.println(curatorEvent);
            }
        }).forPath("app2");
    }

    @After
    public void close(){
        if (client != null){
            client.close();
        }
    }

}
