ch07-spring-mybatis : spring和mybatis的集成

步骤：
1.新建maven项目
2.加入maven的依赖
 1）spring依赖
 2）mybatis依赖
 3）mysql驱动
 4）spring的事务的依赖
 5）mybatis 和spring 集成的依赖 ： mybatis 官方体用的，用来在spring 项目中创建mybatis的SqlSessionFactory，dao对象的

3.创建实体类
4.创建dao接口和 mapper文件
5.创建mybatis 主配置文件
6.创建Service 接口和实现类，属性是 dao
7.创建spring 的配置文件 ： 声明mybatis的对象交给spring创建
    1）数据源 DateSource
    2）SQlSessionFactory
    3）Dao对象




