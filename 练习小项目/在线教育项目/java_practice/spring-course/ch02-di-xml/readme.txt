ch02-di-xml : 在spring 的配置文件中,给java对象的属性赋值

di :dependency injection 依赖注入,表示创建对象给属性赋值

实现语法有两种:
1. 在spring 的配置文件中 , 使用标签和属性完成, 叫做基于 XML 的 di 实现
2. 使用spring中的注解, 完成属性赋值, 叫做基于朱姐姐的 di 实现

di 的语法分类

1.set 注入 ( 设置注入) : spring 调用类的set方法,在ser方法可以实现属性的赋值
        80% 左右都是使用的 set 注入

2.构造注入, spring 调用类的有参数构造方法,创建对象.在构造方法中完成赋值





实现步骤:
1.创建maven项目
2.加入maven依赖
 spring 依赖,版本
 juint依赖

 3.创建类(接口和它得实现类)
    和没有使用框架一样,就是普通得类

4.创建 spring 需要使用得配置信息
    声明类得信息,这些类由spring 创建和管理

    通拓spring 语法 ,完成属性的赋值

5. 测试spring创建