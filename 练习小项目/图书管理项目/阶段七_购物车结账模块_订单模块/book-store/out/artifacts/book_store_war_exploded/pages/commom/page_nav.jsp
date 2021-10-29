<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/10/27
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">

    <%--			当第一页时不显示，首页和上一页--%>

    <c:if test="${requestScope.page.pageNo >1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>

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
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
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
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
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
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
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
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>

                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <%--			页码输出的结束--%>






    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
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
                location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
            });
        })
    </script>
</div>

<%--		分页条的结束--%>