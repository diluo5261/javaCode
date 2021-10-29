<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

	<%--	静态包好javsscript css--%>
	<%@include file="/pages/commom/head.jsp"%>

	<script type="text/javascript">
		$(function () {

			//给删除按钮提供单击事件
			$("a.deleteItem").click(function () {
				return confirm("确定要删除《"+$(this).parent().parent().find("td:first").text()+"》商品吗?");
			});

		//		给清空购物车按钮提供单击事件
			$("#clearCart").click(function (){

				return confirm("确定要清空购物车所有商品吗?");
			});

		//	给输入框绑定 onchange事件
			$(".updateCount").change(function () {
				var name = $(this).parent().parent().find("td:first").text();
				var id = $(this).attr("bookId");
				//获取商品的数量
				var count = this.value;
				if (confirm("确定要将商品"+name+"的数量修改为："+count+"吗？")){
					location.href="${basePath}cartServlet?action=updateCount&count="+count+"&id="+id;

				}else{
					//dafaultValue属性是表单项Dom对象的属性，它表示默认的value属性值
					this.value = this.defaultValue;
				}
			});


		});
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%--			静态包含,登录成功后的页面--%>
		<%@ include file="/pages/commom/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲,当前购物车为空,请开始你的购物吧!!!</a></td>

				</tr>

			</c:if>

			<c:if test="${not empty sessionScope.cart.items}">

				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td><input class="updateCount" type="text" value="${entry.value.count}"
								   bookId="${entry.value.id}" style="width: 60px"> </td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=delItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

			

		</table>

		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=creatOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%--	静态包含页脚内容--%>
	<%@ include file="/pages/commom/foot.jsp"%>
</body>
</html>