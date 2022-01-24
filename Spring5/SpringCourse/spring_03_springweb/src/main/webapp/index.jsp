<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>

<h2>单个接收参数</h2>
<form action="/spring_03/register">
    姓名:<input type="text" name="name"/><br/>
    年龄:<input type="text" name="age"/><br/>
    <input type="submit" value="register">
</form>
<br/>
<h2>对象接收参数</h2>
<form action="/spring_03/register1">
    姓名:<input type="text" name="name"/><br/>
    年龄:<input type="text" name="age"/><br/>
    <input type="submit" value="register">
</form>

<br/>
<form action="/spring_03/upload" method="post" enctype="multipart/form-data">
    名字:<input type="text" name="username"/><br/>
    文件:<input type="file" name="upload"/><br/>
    <input type="submit" value="提交"/><br/>

</form>

<br/>
<form action="/spring_03/mulupload" method="post" enctype="multipart/form-data">
    名字：<input type="text" name="username"/><br/>
    文件1：<input type="file" name="uploadFiles"/><br/>
    文件2：<input type="file" name="uploadFiles"/><br/>
    文件3：<input type="file" name="uploadFiles"/><br/>
    <input type="submit" value="提交"/><br/>
</form>

</body>
</html>