<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/11/9
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%= basePath%>"/>
</head>
<body>

<div style="margin-left: 400px">
    <p>订单列表</p>
    <h3>订单 : ${myorder.id}</h3>
    <h3>订单商品:${myorder.goodName}</h3>

</div>

</body>
</html>
