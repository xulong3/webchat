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
				<input id="account" type="text" disabled="disabled" class="auth-input">
			
			</div>
			<div class="row auth-row">
				<span class="auth-span">我的昵称</span>
				<input id="nickname" type="text" disabled="disabled" class="auth-input">
				<i id="nickname-i" class="fa fa-pencil-square-o fa-2x down-i"></i>
				<i id="nickname-save" class="fa fa-floppy-o fa-2x down-i"></i>
			</div>
			<div class="row auth-row">
				<span class="auth-span">我的邮箱</span>
				<input id="email" type="text" disabled="disabled" class="auth-input">
				<i id="email-i" class="fa fa-pencil-square-o fa-2x down-i"></i>
				<i id="email-save" class="fa fa-floppy-o fa-2x down-i"></i>
			</div>
			
			
			
			<div class="row auth-row">
				<span id="update-pwd-span" class="auth-span">修改密码</span>
				<i id="edit-i" class="fa fa-pencil-square-o fa-2x down-i"></i>
				
				
				
			</div>
			
			<div id="pwd-row" class="row auth-row" closed="true">
				<input id="old-pwd" type="password" class="auth-pwd" placeholder="旧密码">
				<input id="new-pwd" type="password" class="auth-pwd" placeholder="新密码">
				<input id="new-pwd-2" type="password" class="auth-pwd" placeholder="确认密码">
				<i id="pwd-save" class="fa fa-floppy-o fa-2x down-i"></i>
			</div>
			
			
		</div>
	
		<div style="clear: both;"></div>
	</div>
	
		
		
</body>
</html>