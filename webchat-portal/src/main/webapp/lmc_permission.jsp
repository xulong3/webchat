<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限设置页面</title>
<jsp:include page="head.jsp"></jsp:include>

<script type="text/javascript" src="./js/frame.js"></script>
<script type="text/javascript" src="./js/lmc_permission.js"></script>
</head>
<body>
	<div id="permission-container">
	
		<jsp:include page="lmc.jsp">
			<jsp:param value="5" name="nodeid"/>
		</jsp:include>
		
		<div id="permissionl-content" style="height: 100%;float: left;width: 78%;padding-left: 50px;padding-top: 20px;">
			好友认证方式设置：
			<select id="validate-way">
				<option value="0">任何人可加我为好友</option>
				<option value="1">需要验证信息</option>
				<option value="2">任何人不可加我为好友</option>
			</select>
			
			<br><br>
			
			朋友圈权限设置：
			<select id="friend-circle">
				<option value="0">任何人可见</option>
				<option value="1">三天之内可见</option>
				<option value="2">任何人不可见</option>
			</select>
		
			
		</div>
	
		<div style="clear: both;"></div>
	</div>
	
		
		
</body>
</html>