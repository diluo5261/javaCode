<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/11/9
  Time: 13:47
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
    <title>首页</title>
<%--    <base href="<%=basePath%>">--%>
    <script type="text/javascript">
        var url_prefix="${pageContext.request.contextPath}"
        function buy(userId,goodName,price,amount) {
            window.location.href=url_prefix+"/buy/goods?userId="+userId+"&name="+goodName+"&price="+price
                +"&amount="+amount;
        }
    </script>
</head>
<body>
<p>商品列表</p>
<table border="1" cellpadding="1" cellspacing="1" width="60%">
    <tr>
        <td>商品名称</td>
        <td>单价</td>
        <td>库存</td>
        <td>操作</td>
    </tr>
    <tr>
        <td>thinkpad</td>
        <td>5262</td>
        <td>100</td>
        <td>购买</td>
    </tr>
    <tr>
        <td>huawei</td>
        <td>6666</td>
        <td>1060</td>
        <td><a href="javascript:void(0)" onclick="buy(2,'thinkpad',6666,1)">购买</a></td>
    </tr>
</table>
<div style="margin-left: 400px">我的收件人地址</div>

</body>
</html>
