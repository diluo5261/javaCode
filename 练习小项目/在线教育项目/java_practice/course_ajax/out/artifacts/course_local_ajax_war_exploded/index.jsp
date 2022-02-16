<%--
  Created by IntelliJ IDEA.
  User: 34391
  Date: 2021/10/24
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>局部刷新</title>

</head>
<body>
<p>局部刷新ajax-计算bmi</p>
<div>
<%--    没有使用form--%>
    名字:<input type="text" name="name"><br>
    身高(公斤):<input type="text" name="w"><br>
    体重(米):<input type="text" name="h"><br>

    <input type="submit" value="计算bmi" onclick="doAjax">


</div>

<script type="text/javascript">
    function doAjax() {
        //使用内存中的异步对象,代替浏览器发起请求.异步对象那个使用js创建和管理
        //1.创建异步事件
        var data = new XMLHttpRequest();

        //2.绑定事件
        data.onreadystatechange = function () {
            // alert("readyState属性值==="+data.readyState)

            if (data.readyState == 4 && data.status == 200){
                alert(data.responseText);
            }

        }

        //获取dom对象的value属性值
        var name= document.getElementById("name").value;
        var w = document.getElementById("w").value;
        var h = document.getElementById("h").value;

        var param ="name="+name+"&w="+w+"&h="+h;
        alert("param"+param);
        //3.初始请求数据
        data.open("get","bmiAjax?"+param,true);
        //4.发起请求
        data.send();
    }


</script>
</body>
</html>
