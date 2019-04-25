


//获取账号
var params={};
UrlSearch(params);

function loadHistoryMsg(){
	$.ajax({
		type:'post',
		url:ctx+'/getAllMessage',
		data:{
			sender:userObj.account,
			receiver:params.account
		},
		dataType:'json',
		success:function(result){
			result=JSON.parse(result);
			for ( var index in result) {
				$("#msg-ul").append("<li style='color: blue;'>我&nbsp;&nbsp;"+result[index].sendTime
						+"</li><li style='display: inline-block;margin-left: 10px;'>"+result[index].message+"</li>");
			}
			
		}
		
	});
}





$(function(){
	
	$("#chat-list-container").css('height',frame);
	loadHistoryMsg();
	
	//页面初始化
	$("#chat-list-ul").append("<li><div  style='border-bottom: 1px solid black;'>" 
			+"<img src='http://localhost:81/"+params.portrait+"' style='width: 50px;height: 50px;display: inline-block;margin-left: 15px;'>"
			+params.showName+"</div></li>");
	$("h3").text(params.showName);
	
	//发送消息按钮
	$("#send-msg-btn").click(function(){
		if($("textarea").val()==''){
			alert('不能发送空白消息');
			return;
		}
		var msg=$("textarea").val();
		$("textarea").val('');
		//消息记录进入文件
		$.ajax({
			type:'post',
			url:ctx+'/saveMessage',
			data:{
				sender:userObj.account,
				receiver:params.account,
				message:msg
			},
			dataType:'json',
			success:function(result){
				result=JSON.parse(result);
				
				$("#msg-ul").append("<li style='color: blue;'>我&nbsp;&nbsp;"+result.sendTime
						+"</li><li style='display: inline-block;margin-left: 10px;'>"+msg+"</li>");
				
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
							
							//sendMsg(params.account,msg);
						}
					}
					
				});
				
			}
			
		});
		
		
		
	});
	
	
	$("#input-area").keydown(function(event){
		if(event.keyCode==13){
			$("#send-msg-btn").trigger('click');
			return false;
        }
	});
	
	
	
	
	
	
});