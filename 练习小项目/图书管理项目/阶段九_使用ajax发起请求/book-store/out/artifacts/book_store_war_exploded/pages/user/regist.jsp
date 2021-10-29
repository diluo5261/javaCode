<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%--	静态包好javsscript css--%>
<%@include file="/pages/commom/head.jsp"%>

	<script type="text/javascript">
		//页面加载完成之后
		$(function () {
			//给注册绑定单击事件
			$("#sub_btn").click(function () {

				//验证用户名:必须有由字母,数字下划线组成,并且长度位5到12位
				//1.获取输入框中的信息
				var usernameText = $("#username").val();

				//2.创建正则表达式
				var grep = /^\w{5,12}$/;

				//3.使用test方法进行验证
				// if (!grep.test(usernameText)){
				// 	//4.提示用户结果
				// 	$("span.errorMsg").text("用户名不合法!");
				//
				// 	return false;
				// }

				//验证密码:必须由字母,数字下划线组成,并且长度5到12位

				//获取密码输入框信息
				var passwordText = $("#password").val();
				if (!grep.test(passwordText)){
					$("span.errorMsg").text("密码不合法!");

					return false;
				}

				//确认密码,和密码相同
				//1.获取确认密码信息
				var confirmPassword = $("#repwd").val();

				if (passwordText != confirmPassword){
					$("span.errorMsg").text("两次密码不匹配!");
					return false;
				}

				//邮箱验证: xxxx@xxx.com
				//1.获取邮箱里的内容
				var emailText = $("#email").val();

				//2.创建正则表达式
				var emailGrep = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;

				//3.使用test方法验证是否合法
				if (!emailGrep.test(emailText)){
					//4.提示用户
					$("span.errorMsg").text("邮箱格式不合法!");
					return false;
				}

				//验证码:现在只需要验证用具已输入.
				//获取验证码输入框信息
				var codeTest = $("#code").val();

				//取出字符串前后的空格
				codeTest = $.trim(codeTest);
				if (codeTest == null || codeTest==""){
					$("span.errorMsg").html("验证码不能为空");
					return false;

				}
			});
			
			//给图片绑定单击事件,更新图片
			$("#code_img").click(function () {
				//再事件响应的function函数中有一个this对象,这个this对象,时当前正在响应事件的dom对象
				//src属性表示验证码img标签的图片路径,它可读可写
				this.src="${basePath}/kaptcha.jpg?d="+new Date();
				
			});
		});

	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="userServlet" method ="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"  value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" value="${requestScope.password}"/>
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" value="${requestScope.password}"/>
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email" value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" id="code" name="code" value="${requestScope.code}"/>
									<img alt=""  id="code_img" src="http://localhost:8080/book/kaptcha.jpg" style="float: right; margin-right: 1px ;width:200px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--	静态包含页脚内容--%>
		<%@ include file="/pages/commom/foot.jsp"%>
</html>