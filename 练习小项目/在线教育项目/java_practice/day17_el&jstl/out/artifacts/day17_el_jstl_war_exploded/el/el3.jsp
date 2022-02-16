<%@ page import="com.dilo.domain.User" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/8/31
  Time: 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el3获取数据</title>
</head>
<body>
<%
    User user = new User("zhangsan",18,new Date());

    request.setAttribute("u",user);

    List list = new ArrayList();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");

    request.setAttribute("l",list);


    Map map = new HashMap();
    map.put("sname","李四");
    map.put("sage",25);
    map.put("gender","男");
    map.put("user",user);
    request.setAttribute("m",map);



%>

<h3>el获取对象中的值</h3>
${requestScope.u.name}<br/>
${u.name}<br/>
${u.birthday}<br/>
<%--${u.birStr}<br/>--%>
${reu.BirStr}<br>


<h3>获取list数据</h3>
${l}<br>
${l[0]}<br>
${l[1]}<br>
${l[2]}<br>
${l[10]}<br>

<h3>el获取map值</h3>
${m.gender}<br>
${m.user.name}<br>
</body>
</html>
