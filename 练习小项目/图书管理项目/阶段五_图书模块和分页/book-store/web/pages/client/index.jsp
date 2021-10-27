<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	
	<%@include file="/pages/commom/head.jsp"%>
	
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<a href="pages/user/login.jsp">登录</a> |
				<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/client/client.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="" method="get">
					价格：<input id="min" type="text" name="min" value=""> 元 - 
						<input id="max" type="text" name="max" value=""> 元 
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有3件商品</span>
				<div>
					您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
				</div>
			</div>

			<c:forEach items="${requestScope.page.items}" var="book">

				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.img_path}" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${ book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button>加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>


		</div>

		<div id="page_nav">

			<%--			当第一页时不显示，首页和上一页--%>

			<c:if test="${requestScope.page.pageNo >1}">
				<a href="client/bookServlet?action=page&pageNo=1">首页</a>
				<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>

			</c:if>

			<%--页码输出的开始--%>
			<%--情况一：--%>
			<c:choose>
				<c:when test="${requestScope.page.pageTotal <= 5}">
					<c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
						<c:if test="${i == requestScope.page.pageNo}">
							【${i}】
						</c:if>

						<c:if test="${i != requestScope.page.pageNo}">
							<a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>
						</c:if>

					</c:forEach>
				</c:when>

				<c:when test="${requestScope.page.pageTotal >5}">
					<c:choose>
						<%--						情况一：当前页码为前3个：1，2，3的情况，页码范围时1-5--%>
						<c:when test="${requestScope.page.pageNo <= 3}">
							<c:forEach begin="1" end="5" var="i">
								<c:if test="${i == requestScope.page.pageNo}">
									【${i}】
								</c:if>

								<c:if test="${i != requestScope.page.pageNo}">
									<a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>
								</c:if>

							</c:forEach>
						</c:when>


						<%--						情况二：当前页码为最后3个，8，9，10，页码范围时：--%>
						<c:when test="${requestScope.page.pageNo> requestScope.page.pageTotal-3}">

							<c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
								<c:if test="${i == requestScope.page.pageNo}">
									【${i}】
								</c:if>

								<c:if test="${i != requestScope.page.pageNo}">
									<a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>

						<%--						情况3：--%>
						<c:otherwise>
							<c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
								<c:if test="${i == requestScope.page.pageNo}">
									【${i}】
								</c:if>

								<c:if test="${i != requestScope.page.pageNo}">
									<a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>

						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<%--			页码输出的结束--%>






			<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
				<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>
			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
			到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定" >
			<script type="text/javascript">
				$(function () {
					//跳到指定页码
					$("#searchPageBtn").click(function () {

						var pageNo = $("#pn_input").val();

						var pageTotal = ${requestScope.page.pageTotal}
						if(pageNo < 0 || pageNo > pageTotal){
							alert("请输入正确的页码！");
							return false;
						}
						//javaScript提供了一个location地址栏对象
						//有一个属性 href ， 可以获取浏览器地址栏中的地址
						//href可读可写
						location.href="${pageScope.basePath}client/bookServlet?action=page&pageNo="+pageNo;
					});
				})
			</script>
		</div>

		<%--		分页条的结束--%>
	
	</div>

<%--	静态包含页脚内容--%>
<%@ include file="/pages/commom/foot.jsp"%>
	
	
</body>
</html>