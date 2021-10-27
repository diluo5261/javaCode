<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>

	<%--	静态包含javsscript css--%>
	<%@include file="/pages/commom/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteClass").click(function () {

				//在事件的function函数中,有一个this对象.这个this对象,是当前正在响应事件的dom对象
				/*
				confirm是确认提示框函数
				参数是它的提示内容
				有放个按钮,一个是确认,一个是取消
				返回true表示点击了确认,返回false表示点击取消
				 */
				return confirm("确定要删除《"+$(this).parent().parent().find("td:first").text()+"》?");

			});

		})
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>

		<%--		静态包含manager管理模块的菜单--%>
		<%@include file="/pages/commom/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="book">

				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>

			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>

		</table>
<%--		分页条的开始--%>
		<div id="page_nav">

<%--			当第一页时不显示，首页和上一页--%>

			<c:if test="${requestScope.page.pageNo >1}">
				<a href="manager/bookServlet?action=page&pageNo=1">首页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>

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
							<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
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
									<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
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
									<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
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
									<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>

						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<%--			页码输出的结束--%>






		<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
			<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
			<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
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
						location.href="${pageScope.basePath}manager/bookServlet?action=page&pageNo="+pageNo;
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