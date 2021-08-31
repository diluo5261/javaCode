<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ page import="java.util.List" %>
<%@ page import="com.dilo.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/8/31
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%--需求：在request域中有一个存有User对象的List集合。需要使用jstl+el将list集合数据展示到jsp页面的表格table中--%>
<body>
<%

    List<User> list = new ArrayList<>();
    list.add(new User("张三",18,new Date()));
    list.add(new User("李四",28,new Date()));
    list.add(new User("王五",38,new Date()));

    request.setAttribute("list",list);

%>

<table border="1px" width="500" align="center">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>生日</th>
    </tr>

    <c:forEach items="%{list}" var="user" varStatus="s">
        <tr>
            <td>${s.count} </td>
            <td> ${user.name}</td>
            <td> ${user.age}</td>
            <td> ${user.birthday}</td>


        </tr>
    </c:forEach>



</table>


</body>
</html>
