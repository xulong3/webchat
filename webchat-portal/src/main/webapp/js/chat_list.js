


//获取账号
var params={};
UrlSearch(params);


$(function(){
	
	$("#chat-list-container").css('height',frame);
	
	
	$("#chat-list-ul").append("<li><div  style='border-bottom: 1px solid black;'>" 
			+"<img src='http://localhost:81/"+params.portrait+"' style='width: 50px;height: 50px;display: inline-block;margin-left: 15px;'>"
			+params.showName+"</div></li>");
	$("h3").text(params.showName);
	
	$("#send-msg-btn").click(function(){
		if($("textarea").val()==''){
			alert('不能发送空白消息');
		}
		var msg=$("textarea").val();
		$("textarea").val('');
		//消息记录进入文件
		$.ajax({
			type:'post',
			url:ctx+'/saveMessage',
			data:{
				userAccount:userObj.account,
				friendAccount:params.account,
				message:msg
			},
			dataType:'json',
			success:function(result){
				result=JSON.parse(result);
				
				
				
				$("#msg-ul").append("<li style='color: blue;'>我&nbsp;&nbsp;"+getFormatTime(result.sendTime)
						+"</li><li style='display: inline-block;margin-left: 20px;'>"+msg+"</li>");
				
			}
			
		});
		
		//查询用户状态
		$.ajax({
			type:'post',
			url:ctx+'/getUserStatus',
			data:{
				token:params.account
			},
			dataType:'text',
			success:function(result){
				
				if(result=="1"){
					
					sendMsg(params.account,msg);
				}
			}
			
		});
		
	});
	
	
});