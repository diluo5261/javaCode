ch09-exceptiom-handle ： 全局的异常处理

异常处理步骤：
1.加入依赖
2.新建一个自定义异常类，MyUserException， 再定义它的子类 NameException，AgeException
3.在controller中抛出NameException，AgeException
4.创建一个普通类，作为全局异常处理类
    1）在类的上面加入@ControllerAdvice
    2）在类中定义方法，方法的上面加入@ExceptionHandler


5.创建处理异常的视图页面
6.创建springmvc的配置文件
    1）组件扫描器，扫描@Controller注解
    2）组件扫描器，嫂买哦@ControllerAdvice所在的包名
    3）声明注解驱动