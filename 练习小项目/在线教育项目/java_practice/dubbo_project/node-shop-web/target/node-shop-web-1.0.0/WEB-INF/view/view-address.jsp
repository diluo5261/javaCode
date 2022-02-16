<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/11/9
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <title>收件人地址</title>
    <base href="<%=basePath%>">

</head>
<body>

<div style="margin-left: 400px">
    <p>地址列表</p>

   <c:forEach items="${list}" var="address">
       <h3>收件人姓名:${address.name}</h3>
       <h3>收件人地址:${address.city} - ${address.street}</h3>
       <h3>收件人联系方式 : ${address.mobile}</h3>
   </c:forEach>

</div>
</body>
</html>
