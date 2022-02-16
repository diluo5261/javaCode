<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/10/14
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <p>提交参数给Controller</p>
        <form action="receiveproperty.do" method="post">
            姓名:<input type="text" name="username"><br>
            年龄:<input type="text" name="userage"><br>
            <input type="submit" value="提交参数">
        </form>
        <p> <a href="some.do">发起的some.do 的get请求</a> </p>
        <p> <a href="other.do">发起的other.do 的post请求</a> </p>

<p>请求参数和处理器方法的形参名不一样,使用逐个接收参数的方式</p>
<form action="receiveparam.do" method="post">
    姓名:<input type="test" name="rname">
    年龄:<input type="test" name="rage">
    <input type="submit" value="提交">

</form>

        <p>请求参数和处理器方法的形参名不一样,使用对象接收参数的形式 </p>
        <form action="receiveobject.do" method="post">
            姓名:<input type="test" name="rname">
            年龄:<input type="test" name="rage">
            <input type="submit" value="提交">

        </form>

</body>
</html>
