<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/10/14
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                // $.getJSON("returnVoid-ajax.do","username=张三&userage=12",function (data) {
                // $.getJSON("requestStudentJson.do","username=张三&userage=12",function (data) {
                $.getJSON("requestStudentArray.do","username=张三&userage=12",function (data) {
                    // data从服务器端返回的是json格式的字符串{"name=张三&age=12}
                    // alert(data.rname +" "+ data.rage);

                })
            });
        })


    </script>
</head>
<body>
        <p>处理器方法返回String表示视图名称</p>
        <form action="returnString-View.do" method="post">
            姓名:<input type="text" name="username"><br>
            年龄:<input type="text" name="userage"><br>
            <input type="submit" value="提交参数">
        </form>
        <br>
        <br>

        <p>处理器方法返回String 表示完整的视图路径</p>
        <form action="returnString-View2.do" method="post">
            姓名:<input type="text" name="username"><br>
            年龄:<input type="text" name="userage"><br>
            <input type="submit" value="提交参数">
        </form>

        <br>
        <br>
        <button id="btn">发起ajax请求</button>



</body>
</html>
