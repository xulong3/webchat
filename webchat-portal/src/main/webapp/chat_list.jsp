<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会话列表页面</title>
<jsp:include page="head.jsp"></jsp:include>
<script type="text/javascript" src="./js/frame.js"></script>
<script type="text/javascript" src="./js/chat_list.js"></script>	
	
	
</head>
<body>
	<div id="chat-list-container">
		<div class="col-md-3" style="height: 100%;border-right: 1px solid green;padding:0; ">
		
			<ul style="list-style: none;padding: 0;" id="chat-list-ul">
				
			</ul>
		
		</div>
		<div class="col-md-9" style="height: 100%;padding: 0;overflow: hidden;">
			<div class="row" style="border-bottom: 1px solid green;margin: 0;height: 10.1%;background-color: #CDBE70;padding-left: 20px;">
				<a id="open-history" href="javascript:void(0)">历史记录</a>
				<h3 style="display: inline-block;margin-left: 630px;margin-top: 10px;"></h3>
			
			</div>
			<div id="msg-row" class="row" style="margin: 0;height: 70%;overflow-y:scroll;">
				<ul style="list-style: none;" id="msg-ul">
					
					
				
				</ul>
				
			
			</div>
			<div class="row" style="border-top: 1px solid green;margin: 0;height: 20%;">
				
			
				<textarea id="input-area" style="width: 90%;height: 90%;resize: none;outline: none;float: left;border: none;"></textarea>
				
				<div style="float: left;padding-left: 10px;padding-top: 10px;">
					<button id="send-msg-btn" class="btn-success">发送</button>
				
				</div>
				
				
			</div>
		
		</div>
	
	
	
	
		
	</div>
	
	
	<div class="modal fade" tabindex="-1" role="dialog" id="history">
	<div class="modal-dialog" role="document">
	<div class="modal-content">
		<div class="modal-header">
	        
	        <h4 class="modal-title">历史记录</h4>
		</div>
		      
		<div class="modal-body">
			<div id="modal-msg-row" class="row" style="margin: 0;overflow-y:scroll;height: 300px;">
				<div id="history-tree" style="height: 100%;width: 100%;">
				</div>
				
			
			</div>
		</div>
		
		<div class="modal-footer">
        	<button type="button" class="btn-danger" data-dismiss="modal">关闭</button>
      	</div>      
      	
	</div>
	</div>
	</div>
	
	
	
</body>

</html>