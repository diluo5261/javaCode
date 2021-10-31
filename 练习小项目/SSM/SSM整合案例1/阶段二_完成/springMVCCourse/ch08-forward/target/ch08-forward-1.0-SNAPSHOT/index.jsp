<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/10/14
  Time: 20:41
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
    <title>Title</title>
    <base href="<%=basePath%>" />
</head>
<body>
        <p>发起一个请求，forward</p>
        <form action="doForward.do" method="post">
            姓名：<input type="text" name="name"><br>
            年龄：<input type="text" name="age"><br>
            <input type="submit" value="提交请求"/>
        </form>

        <p>发起一个请求，redirect</p>
        <form action="doRedirect.do" method="post">
            姓名：<input type="text" name="name"><br>
            年龄：<input type="text" name="age"><br>
            <input type="submit" value="提交请求"/>
        </form>

</body>
</html>
