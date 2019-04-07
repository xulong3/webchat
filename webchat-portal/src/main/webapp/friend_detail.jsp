<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好友详情页面</title>
<jsp:include page="head.jsp"></jsp:include>
<script type="text/javascript" src="./js/frame_3.js"></script>
<script type="text/javascript" src="./js/friend_detail.js"></script>
	
	
</head>
<body>
	<div id="friend-detail-container">
		
		
		<div class="row" id="chat-row" style="margin: 0;padding-left: 30px;padding-top: 20px;">
			
			<button id="to_chat_btn" class="btn-success" style="width: 100px;height: 30px;border-radius: 2px;">
				<i class="fa fa-weixin"></i>&nbsp;&nbsp;聊天</button>
		</div>
		
		
		
		<div class="row" id="auth-label-row" style="margin: 0;padding-left: 30px;padding-top: 20px;">
			<h4>身份标签</h4>
			<table border="1" style="width: 80%;" id="auth-label-table">
				<tr>
					<td style="width: 20%;"></td>
					<td style="width: 80%;"></td>
				</tr>
			
			
			</table>
		
		</div>
		<div class="row" id="sys-label-row" style="margin: 0;padding-left: 30px;padding-top: 20px;">
			<h4>系统标签</h4>
			<table border="1" style="width: 80%;" id="sys-label-table">
				<tr>
					<td style="width: 20%;"></td>
					<td style="width: 80%;"></td>
				</tr>
			
			
			</table>
		</div>
		
		
		
		
	</div>
	
</body>
</html>