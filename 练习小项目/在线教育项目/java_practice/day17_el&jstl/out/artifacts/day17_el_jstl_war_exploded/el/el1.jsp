<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/8/30
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>算数运算符</h1>
加:${3+1}<br/>
减:${3-1}<br/>
乘:${3*4}<br/>
除 : ${6/3}<br/>
除:${6 div 3}<br/>
取余:${5 mod 3}

<h1>比较运算符</h1>
比较:${3 ==4}
${4 ==4 }

<h1>逻辑运算符</h1>
${3 >4 and 5>4}<br/>
${6 >4 && 5> 4}<br/>

<h1>isempty</h1>
<%
    String str = "";
    request.setAttribute("str",str);

    List list = new ArrayList();
    request.setAttribute("list",list);

    System.out.println(request.getMethod());

%>

${empty str}<br/>
${not empty list}

</body>
</html>
