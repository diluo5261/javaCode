<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>

	<%--	静态包好javsscript css--%>
	<%@include file="/pages/commom/head.jsp"%>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">结算</span>


		<%--			静态包含,登录成功后的页面--%>
		<%@ include file="/pages/commom/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为2937474382928484747</h1>
		
	
	</div>

	<%--	静态包含页脚内容--%>
	<%@ include file="/pages/commom/foot.jsp"%>
</body>
</html>