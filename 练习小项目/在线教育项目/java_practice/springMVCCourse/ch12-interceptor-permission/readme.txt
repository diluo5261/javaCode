ch12-interceptor-permission：使用拦截器检查登录的用户是不是能访问系统

实现步骤：
1.新建maven
2.修改 web。xml注册中央调度器
3.新建index.jsp发起请求
4.创建MyController处理请求
5.创建结果show.jsp
    创建一个login.jsp,模拟登录(把用户的信息放入到session)
    创建一个jsp,logup.jsp模拟退出登录系统(从Session中删除数据)

6.创建拦截器,从session中获取用户登录数据,验证是否能访问系统
7.创建一个验证的jsp,如果验证视图,给出提示

8.创建springmvc配置文件
    声明组件扫描器,声明拦截器
