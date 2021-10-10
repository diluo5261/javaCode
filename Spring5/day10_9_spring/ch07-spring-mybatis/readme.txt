1. 新建 maven 项目
2.加入maven 依赖
 1)spring 依赖
 2)mybatis 依赖
 3)mysql 驱动
 4)spring 事务依赖
 5) mybatis 的 spring 集成的依赖:mybatis 官方体提供的,用来在spring 项目中创建 mybatis的SqlSessionFactory,dao对象的

3.创建实体类
4.创建 dao 接口 和 mapper 文件
5.创建 mybatis 注配置文件
6.创建service接口和实现类,属性是 dao
7.创建 spring 的配置文件:声明mybatis 的对象交给spring创建
    1)数据源 DateSource
    2)SqlSessionFactory
    3)Dao对象
    4)声明自定义的service


8.创建测试类,获取service对象,通过service 调用dao完成数据库的访问
