ch03_return :处理器方法的返回值表示请求的处理结果
1.ModelAndView : 有数据有视图,可以对视图执行forward
2.String : 表示视图.可以是逻辑名称,也可以是完整视图路径
3.void:不能表示数据,也不能表示视图,在处理ajax的时候,可以使用 void 返回值,
        同 HttpServletResponse输出数据.响应ajax请求
        ajax请求服务器返回就是数据,和视图无关

4.Object :例如String , Integer , Map,ListStudent等等都是对象
    对象有属性,属性就是数据,所以返回Object表示数据,和视图无关
    可以使用对象标识数据,响应ajax请求.
    现在做ajax,主要使用json的数据格式.实现步骤

    1.加入处理json工具库的依赖 springmvc默认使用的是 jackson
    2.在springmvc配置文件中加入 <mvc:annotation-driver> 注解驱动
    json = om.writeValueAsString(student)

    3.在处理器方法的上面加入@ResponseBody注解
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
springMVC处理器方法返回 Object ,可以转为 json 输出到浏览器,响应ajax的内部原理

1. <mvc:annotation-driver> 注解驱动
    注解驱动实现的功能是 完成java对象到 json,xml,text,二进制等数据格式的转换
    HttpMessageConveter接口:消息转换器
    功能:定义了,java转为json ,xml等数据格式的方法,这个接口有很多的实现类.
        这些实现类完成 java 对象到json,xml,或二进制数据的转换


下面的两个方法是控制器类把结果输出给浏览器时使用的：
     boolean canWrite(Class<?> var1, @Nullable MediaType var2);
     void write(T var1, @Nullable MediaType var2, HttpOutputMessage var3)

     例如处理器方法
     @RequestMapping(value = "/returnString.do")
     public Student doReturnView2(HttpServletRequest request,String name, Integer age){
             Student student = new Student();
             student.setName("lisi");
             student.setAge(20);
             return student;
     }

     1）canWrite作用检查处理器方法的返回值，能不能转为var2表示的数据格式。
       检查student(lisi，20)能不能转为var2表示的数据格式。如果检查能转为json，canWrite返回true
       MediaType：表示数格式的， 例如json， xml等等

     2）write：把处理器方法的返回值对象，调用jackson中的ObjectMapper转为json字符串。
        json  = om.writeValueAsString(student);


 2. @ResponseBody注解
   放在处理器方法的上面， 通过HttpServletResponse输出数据，响应ajax请求的。
           PrintWriter pw  = response.getWriter();
           pw.println(json);
           pw.flush();
           pw.close();






