


//获取账号
var params={};
UrlSearch(params);

if(params.account==undefined){
	//查一下当前的会话列表
	$.ajax({
		type:'post',
		url:ctx+'/getCurrentChatList',
		async:false,
		data:{
			account:userObj.account
			
		},
		dataType:'json',
		success:function(result){
			if(result==''){
				return;
			}
			result=JSON.parse(result);
			params.account=result.account;
			params.portrait=result.portrait;
			params.showName=(result.remark==undefined || result.remark=='')?result.nickname:result.remark;
		}
		
	});
}



function loadTodayMsg(){
	$.ajax({
		type:'post',
		url:ctx+'/getTodayMessage',
		data:{
			sender:userObj.account,
			receiver:params.account
		},
		dataType:'json',
		success:function(result){
			result=JSON.parse(result);
			
			for ( var index in result) {
				var show=result[index].sender==userObj.account?"我":(params.showName);
				$("#msg-ul").append("<li style='color: blue;'>"+show+"&nbsp;&nbsp;"+result[index].sendTime
						+"</li><li style='display: inline-block;margin-left: 20px;'>"+result[index].message+"</li>");
			}
			//滚动条置底
			$("#msg-row")[0].scrollTop = $("#msg-row")[0].scrollHeight;
			
		}
		
	});
}

function initTree(data){
	$('#history-tree').treeview({
	    data:data,
	    showTags:true,
	    onhoverColor:'white',
	    selectedBackColor: 'green',
	    collapseIcon: "glyphicon glyphicon-chevron-down",
	    expandIcon: "glyphicon glyphicon-chevron-right",
	    levels:1,
	    showBorder:false,
	    showCheckbox:true
	});
	
}



$(function(){
	
	$("#chat-list-container").css('height',frame);
	if(params.account==undefined){
		$("#list-content").append("<h2>当前没有任何会话!</h2>");
		$("#chat-content").hide();
		return;
	}
	
	loadTodayMsg();
	
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
						+"</li><li style='display: inline-block;margin-left: 20px;'>"+msg+"</li>");
				
				//滚动条置底
				$("#msg-row")[0].scrollTop = $("#msg-row")[0].scrollHeight;
				
				//查询用户状态
				$.ajax({
					type:'post',
					url:ctx+'/getUserStatus',
					data:{
						token:params.account
					},
					dataType:'text',
					success:function(data){
						
						if(data=="1"){
							
							sendMsg(params.account,msg,result.sendTime);
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
	
	$("#open-history").click(function(){
		/*$.ajax({
			type:'post',
			url:ctx+'/getHistoryMessage',
			data:{
				sender:userObj.account,
				receiver:params.account
			},
			dataType:'json',
			success:function(result){
				result=JSON.parse(result);
				for ( var index in result) {
					$("#modal-msg-ul").append("<li style='color: blue;'>我&nbsp;&nbsp;"+result[index].sendTime
							+"</li><li style='display: inline-block;margin-left: 20px;'>"+result[index].message+"</li>");
				}
				//滚动条置底
				$("#modal-msg-row")[0].scrollTop = $("#modal-msg-row")[0].scrollHeight;
				
			}
			
		});*/
		
		$.ajax({
			type:'post',
			url:ctx+'/loadHistoryMessageTree',
			data:{
				sender:userObj.account,
				receiver:params.account
				
			},
			dataType:'json',
			success:function(result){
				
				result=JSON.parse(result);
				initTree(result);
				$("#history").modal('show')
			}
			
		});
		
	
	});
	
	
	
	
});