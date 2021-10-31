<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/10/31
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>

<html>
<head>
    <title>查询学生</title>
    <base href="<%=basePath%>" />
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript" >
        $(function () {
            //在当前页面加载后，马上加载请求

            loadStudentDara();


            function loadStudentDara(){

                $.getJSON("student/queryStudent.do","",function (resp) {
                    //清除旧的数据
                    $("#info").html("");
                    $.each(resp,function (i,n) {
                        $("#info").append("<tr>")
                            .append("<td>"+n.id+"</td>")
                            .append("<td>"+n.name+"</td>")
                            .append("<td>"+n.age+"</td>")
                            .append("</tr>");
                    })
                })
                /*$.ajax({
                    url:"student/queryStudent.do",
                    type:"get",
                    dataType:"json",
                    success:function(data){
                        alert("data="+data)
                    }
                })*/
            }
        })
    </script>


</head>
<body>
    <div align="center">
        <table>
            <tr>
                <th>序号</th>
                <th>姓名</th>
                <th>年龄</th>

            </tr>

            <tbody id="info">

            </tbody>
        </table>
        <input type="button" id="btnLoader" value="查询数据">
    </div>


</body>
</html>
