<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单页面</title>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/menu.css">
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/websocket.js"></script>	
	
	
</head>
<body>
	<div id="menu-container" style="overflow: hidden;">
		<div class="row" style="height: 12%;border-bottom: 1px solid black;">
			<div class="col-md-3">
				<img src="logo.jpg" class="img-60">
				<a class="h3" href="javascript:void(0)">多人在线网页聊天室</a>
			</div>
			
			<div class="col-md-6" style="padding-top: 12px;">
			
				<div class="row" style="height: 30px;">
					<div class="col-sm-3">
						<a style="font-size: 20px;" href="javascript:void(0)">添加好友</a>
					
					</div>
					<div class="col-sm-3">
						<a style="font-size: 20px;" href="javascript:void(0)">添加好友</a>
					
					</div>
				</div>
				<div class="row" style="background-color: blue;height: 10px;">
					<div class="col-sm-2" style="background-color: black;">
					
					</div>
					<div class="col-sm-2" style="background-color: green;">
					
					</div>
				
				</div>
				
				<!-- <a style="font-size: 20px;" href="javascript:void(0)">添加好友</a>
				
				<a style="font-size: 20px;" href="javascript:void(0)">添加好友</a> -->
			</div>
			
			
			
			
			<div class="col-md-2 col-md-offset-1">
				<img id="portrait" class="img-60">
				<a class="h2" id="nickname-a" href="javascript:void(0)"></a>
			</div>
		
			
		</div>
		 
		<div class="row" style="height: 88%; background-color: red;">
			<div class="col-md-10" style="height: 100%;">
			
				<iframe style="width: 100%;height: 100%;" frameborder="0"></iframe>
			
			</div>
			<div class="col-md-2" style="height: 100%;background-color: red;"></div>
		</div>
	
	
	
	
	
	
	
	</div>
	
		
		
</body>
</html>