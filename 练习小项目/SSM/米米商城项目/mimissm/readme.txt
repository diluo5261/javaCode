搭建SSM项目的步骤:
1、新建 maven项目
2、添加 SSM 所有的依赖
3、拷贝jdbc。properties到resource目录下
4、新建 applicationContext_dao.xml文件，进行数据访问层的配置
5、新建 applicationContext——service。xml文件，进行业务逻辑层的配置
6、新建 springmvc。xml文件，配置springmvc的框架
7、新建 sqlMapConfig。xml文件，进行分页插件的配置
8、使用逆向工程生成 pojo 和 mapper 文件
9、开发业务逻辑层，实现登录的判断
10、开发控制器 AdminAction完成登录处理，
11、改造页面，发送登录请求，验证登录。