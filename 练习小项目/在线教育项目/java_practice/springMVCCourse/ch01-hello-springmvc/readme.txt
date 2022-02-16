ch01-spring-springmvc

需求 :用户在页面发起一个请求,请求交给 springMVC 的控制器对象,并显示请求的处理结果(在结果页面显示一个欢迎语句)

实现步骤:
1. 新建web maven 工程
2.加入依赖
    spring-webmvc依赖,间接把 spring的一阿里都加入到项目
    jsp ,servlet 依赖

3.重点 : web.xml中注册 springmvc框架的核心对象 DispatcherServlet
    1) DispatcherServlet 叫做中央处理器,是一个servlet , 它的父类是继承 HTppServlet
    2) DispatcherServlet 页叫做前端控制器 (front controller)
    3) DispatcherServlet 负责接受用户提交的请求,调用其它控制器对象,并把请求得处理结果显示给用户

    只要是servlet 必定有中央调度器对象

4.创建一个发起请求得页面 index.jsp
5.创建控制器类（处理器类，处理请求）
    1) 在类得上面加入 @Controller 注解,创建对象, 并放入到 springmvc 容器中
    2) 在类中得方法上面加入 @RequestMapping 注解

6.创建一个作为结果得 jsp , 子安是请求处理得结果
7.创建 springmvc 得配置文件 (spring得配置文件一样)