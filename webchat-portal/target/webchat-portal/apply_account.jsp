<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请账号页面</title>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/apply_account.css">
<script type="text/javascript" src="./js/apply_account.js"></script>

</head>
<body>
	
	<h1>网页聊天室</h1>
	<div id="content">
		昵称<input type="text" name="nickname" id="nickname" class="form-control">
		密码<input type="password" name="password" id="password" class="form-control">
		确认密码<input type="password" name="password2" id="password2" class="form-control">
		注册邮箱<input type="text" name="email" id="email" class="form-control">
		<br><div id="info"></div><br>
		<input id="apply-btn" type="button" value="提交账号申请" class="btn-success">
		
		<a href="login.jsp">去登录</a>
	</div>
	
		
		
</body>
</html>