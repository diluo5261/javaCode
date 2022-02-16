<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/10/31
  Time: 14:53
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
    <title>查询学生</title>
    <base href="<%=basePath%>"/>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
</head>
<body>
<div align="center">
    <table>
        <tr>
            <td>学生</td>
            <td>姓名</td>
            <td>年龄</td>
        </tr>
    </table>

</div>


</body>
</html>
