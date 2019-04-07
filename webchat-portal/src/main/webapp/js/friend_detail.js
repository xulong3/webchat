
//获取账号
var params={};
UrlSearch(params);

var friendVo=parent.friendVoList[params.account];




$(function(){
	
	$("#friend-detail-container").css('height',frame);
	
	for ( var index in friendVo.authLabel) {
		$("#auth-label-table").append("<tr><td>"+index+"</td><td>"+(friendVo.authLabel)[index]+"</td></tr>");
	}
	
	for ( var index in friendVo.sysLabel) {
		$("#sys-label-table").append("<tr><td>"+index+"</td><td>"+(friendVo.sysLabel)[index]+"</td></tr>");
	}
	
	
	$("#to_chat_btn").click(function(){
		//如果是第一次，创建聊天文件
		$.ajax({
			type:'post',
			url:ctx+'/createP2pChatFile',
			data:{
				userAccount:userObj.account,
				friendAccount:params.account
			},
			dataType:'text',
			success:function(result){
				
			}
			
		});
		
		
		
		
		var frame=parent.parent.$("iframe");
		$(frame).attr('src','chat_list.jsp?account='+friendVo.account+"&portrait="+friendVo.sysLabel.portrait+"&showName="+friendVo.showName);
	});
	
	
});