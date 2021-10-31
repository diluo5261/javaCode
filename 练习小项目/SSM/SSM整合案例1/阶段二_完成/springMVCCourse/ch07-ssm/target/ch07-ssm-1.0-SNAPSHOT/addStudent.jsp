<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/10/31
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>

<html>
<head>
    <title>学生注册</title>
    <base href="<%=basePath%>">
</head>
<body>
<div>
    <form action="student/addStudent.do" method="post">
        <table>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="name"/> </td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td><input type="email" name="email"/> </td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td><input type="text" name="age"/> </td>
            </tr>
            <tr>
                <td>&emsp;</td>
                <td><input type="submit" value="注册">注册111</td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
