<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/8/28
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    System.out.println("hello world");
    String contextPath = request.getContextPath();
    response.getWriter().write("666");

  %>
  $END$
  </body>
</html>
