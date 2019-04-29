<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好友列表配置页面</title>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/friend_list_config.css">
<script type="text/javascript" src="./js/frame_3.js"></script>
<script type="text/javascript" src="./js/friend_list_config.js"></script>
	
	
</head>
<body>
	<div id="friend-list-config-container" style="padding-top: 20px;">
		
		<div class="row" style="padding-left: 50px;margin-bottom: 20px;">
			<input type="text" id="search-input" placeholder="搜索好友">
			<img src="search.png" id="search-icon">
			<button id="search-btn" class="btn-success">搜索</button>
		</div>	
		
		<div class="row" style="padding-left: 50px;">
			<input type="text" name="friend-account" placeholder="输入好友的账号">
			<button id="add-friend" class="btn-success">添加好友</button>
		</div>	
		
		
		<div id="friend-apply-addition">
			<div class="row">
				备注：<br><input type="text" name="userRemark">
			</div>	
			<div class="row">
				验证信息：<br><textarea rows="5" cols="50" name="validateInfo" style="resize: none;"></textarea>
			</div>	
			
			<div class="row">
				<button id="submit-btn" class="btn-success">提交</button>
				<button id="cancel-btn" class="btn-success">取消</button>
			</div>	
		</div>
	
		<div class="row" style="padding-left: 50px;margin-top: 20px;">
			<button class="btn-success" id="query_apply_byme">查看我发出的好友申请</button>
		</div>	
		
		<div class="row" id="apply_byme_div">
			<h3 style="margin-bottom: 20px;padding-left: 25px;">我发出的好友申请</h3>
			<ul id="apply_byme_ul">
			</ul>
		
		</div>
		
		<div class="row" style="padding-left: 50px;margin-top: 20px;">
			<button class="btn-success" id="query_applied">查看我收到的好友申请</button>
		</div>	
		
		<div class="row" id="applied_div">
			<h3 style="margin-bottom: 20px;padding-left: 25px;">我收到的好友申请</h3>
			<ul id="applied_ul">
			</ul>
		
		</div>
	</div>
	
</body>
</html>