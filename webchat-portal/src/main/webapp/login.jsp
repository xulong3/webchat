<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/login.css">
<script type="text/javascript" src="./js/login.js"></script>

</head>
<body>
	
	<h1>网页聊天室</h1>
	<div id="content">
		<span id="way-span">账号</span><input type="text" name="token" id="token" class="form-control">
		密码<input type="password" name="password" id="password" class="form-control">
		
		
		<br>
		
		<a id="login-way" href="javascript:void(0)">使用邮箱登录</a><br><br>
		<div id="info"></div><br>
		<input id="login-btn" type="button" value="登录" class="btn-success">
		<a id="to-reg" href="apply_account.jsp">去注册</a>
	</div>
	
		
		
</body>
</html>