link-orderservice-provider:dubbo的服务提供者.提供接口,让消费者调用

实现步骤:
1、新建 maven
2、加入依赖
 1）spring
 2）dubbo

3、新建保存数据的类 Order，实现序列化接口
4、新建业务接口 OrderService，定义方法 creatOrder
5、新建业务接口的实现类，通过容器创建和管理对象

6、新建spring 的配置文件，通过容器创建和管理对象
 1）声明dubbo的服务名称
 2）声明服务的接口，暴露接口
 3）声明服务接口的实现类对象

7、新建测试类，测试spring的配置文件
8、把服务提供者的jar 安装到本地的服务仓库


