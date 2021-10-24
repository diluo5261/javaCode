
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>全局刷新</title>
  </head>
  <body>
    <p>全局刷新计算 BMI</p>
  <form action="/bmiPrint" method="get">
    姓名: <input type="text" name="name"><br>
    体重(公斤): <input type="text" name="w"><br>
    身高(米) : <input type="text" name="h"><br>

    <input type="submit" value="提交">
  </form>

  </body>
</html>
