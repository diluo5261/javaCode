<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/8/30
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el获取域中的数据</title>
</head>
<body>
<%
    session.setAttribute("name","zhangsan");
    request.setAttribute("name","张三");
    session.setAttribute("age",15);
    session.setAttribute("str","");

%>

<h3>el获取值</h3>
${requestScope.name}<br/>
${sessionScope.name}<br/>
${name}<br/>
${age}<br/>
${str}

</body>
</html>
