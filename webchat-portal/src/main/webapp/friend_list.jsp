<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好友列表页面</title>
<jsp:include page="head.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="./css/friend_list.css">
<script type="text/javascript" src="./js/frame.js"></script>
<script type="text/javascript" src="./js/friend_list.js"></script>	
	
	
</head>
<body>
	<div id="friend-list-container">
		<div id="friends" class="col-sm-3">
			
			<input type="text" id="search-input" placeholder="搜索好友">
			<i id="config-i" class="fa fa-chevron-circle-right fa-2x"></i>
			
			<div style="height: 1px;border: 1px solid black;"></div>
			<img src="search.png" id="search-icon">
			<ul id="friend-list-ul">
			
				<li class="friend-li">
					<img class="friend-img" src="default-user.jpg">
					<div class="friend-info">
					
						<span class="h4">yy</span>
					</div>
					
				</li>
			</ul>
		
		</div>
		
		<div id="friend-data" class="col-sm-9" style="height: 100%;padding: 0;">
		
			<iframe style="width: 100%;height: 100%;" frameborder="0"></iframe>
			
			
		</div>
	
	
	
	</div>
	
</body>
</html>