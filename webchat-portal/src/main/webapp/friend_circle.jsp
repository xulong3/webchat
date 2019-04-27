<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>朋友圈页面</title>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/friend_circle.css">
<script type="text/javascript" src="./js/frame.js"></script>
<script type="text/javascript" src="./js/friend_circle.js"></script>	
	
	
</head>
<body>
	<div id="friend-circle-container" style="overflow: hidden;">
		<div id="friend-dynamic" class="col-sm-8">
			<ul id="dynamic-ul">
				
			</ul>
			
		</div>
		
		<div id="dynamic" class="col-sm-4" style="height: 100%;padding-left: 10px;padding-top: 10px;">
		
			<a id="query-btn" href="javascript:void(0)" style="">查询我的动态</a><br><br>
			
			<a id="query-all-btn" href="javascript:void(0)" style="">查询全部动态</a><br><br>
			
			<a href="javascript:void(0)" style="">发表动态</a><br>
			<textarea id="dynamic-textarea" style="width: 90%;height: 30%;resize: none;" 
						placeholder="在此输入动态内容..."></textarea><br>
			
			<button id="publish-btn" class="btn-success">发表</button>
		</div>
	
	
	
	</div>
	
</body>
</html>