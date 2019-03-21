<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>身份标签页面</title>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/lmc_auth.css">
<script type="text/javascript" src="./js/frame.js"></script>
<script type="text/javascript" src="./js/lmc_auth.js"></script>
</head>
<body>
	<div id="auth-container">
	
		<jsp:include page="lmc.jsp">
			<jsp:param value="1" name="nodeid"/>
		</jsp:include>
		
		<div style="height: 100%;float: left;width: 78%;padding-left: 80px;padding-top: 40px;">
			<div class="row auth-row">
				<span class="auth-span">我的账号</span>
				<input type="text" disabled="disabled" class="form-control auth-input">
			
			</div>
			<div class="row auth-row">
				<span class="auth-span">我的昵称</span>
				<input type="text" class="form-control auth-input">
			
			</div>
			<div class="row auth-row">
				<span class="auth-span">我的邮箱</span>
				<input type="text" class="form-control auth-input">
			
			</div>
			<div class="row auth-row">
				<span class="auth-span">修改密码</span>
				<input type="checkbox" checked id="sw"/>
			</div>
			
			<div class="row">
				
				<input type="password" class="form-control auth-input" placeholder="旧密码">
				<input type="password" class="form-control auth-input" placeholder="新密码">
				<input type="password" class="form-control auth-input" placeholder="确认密码">
			</div>
			
			
			
			<button class="btn-success">保存</button>
		</div>
	
		<div style="clear: both;"></div>
	</div>
	
		
		
</body>
</html>