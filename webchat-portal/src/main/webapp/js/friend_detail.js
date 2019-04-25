
//获取账号
var params={};
UrlSearch(params);
var friendVo=parent.friendVoList[params.account];

function loadFriendDetail(){
	
	$.ajax({
		type:'post',
		url:ctx+'/loadFriendAllLabel',
		data:{
			account:params.account,
		},
		dataType:'json',
		success:function(result){
			result=JSON.parse(result);
			//添加身份标签
			$("#auth-label-table").append("<tr><td>昵称</td><td>"+result[0].nickname+"</td></tr>");
			$("#auth-label-table").append("<tr><td>账号</td><td>"+result[0].account+"</td></tr>");
			$("#auth-label-table").append("<tr><td>邮箱</td><td>"+result[0].email+"</td></tr>");
			$("#auth-label-table").append("<tr><td>注册时间</td><td>"+result[0].regTime+"</td></tr>");
			$("#auth-label-table").append("<tr><td>激活时间</td><td>"+result[0].actTime+"</td></tr>");
			//添加系统标签
			
			$("#sys-label-table").append("<tr><td>真实姓名</td><td>"+result[1].realname+"</td></tr>");
			$("#sys-label-table").append("<tr><td>英文名</td><td>"+result[1].englishName+"</td></tr>");
			$("#sys-label-table").append("<tr><td>性别</td><td>"+result[1].sex+"</td></tr>");
			$("#sys-label-table").append("<tr><td>生日</td><td>"+result[1].birthday+"</td></tr>");
			$("#sys-label-table").append("<tr><td>年龄</td><td>"+result[1].age+"</td></tr>");
			$("#sys-label-table").append("<tr><td>星座</td><td>"+result[1].constellation+"</td></tr>");
			$("#sys-label-table").append("<tr><td>手机号码</td><td>"+result[1].phone+"</td></tr>");
			$("#sys-label-table").append("<tr><td>公司</td><td>"+result[1].company+"</td></tr>");
			$("#sys-label-table").append("<tr><td>兴趣爱好</td><td>"+result[1].hobby+"</td></tr>");
			$("#sys-label-table").append("<tr><td>个性签名</td><td>"+result[1].signature+"</td></tr>");
			$("#sys-label-table").append("<tr><td>学校</td><td>"+result[1].school+"</td></tr>");
			$("#sys-label-table").append("<tr><td>现居地</td><td>"+result[1].presentAddress+"</td></tr>");
			$("#sys-label-table").append("<tr><td>家乡</td><td>"+result[1].hometown+"</td></tr>");
			$("#sys-label-table").append("<tr><td>职业</td><td>"+result[1].profession+"</td></tr>");
			
			for ( var index in result[2]) {
				
				$("#label-table").append("<tr><td>"+index+"</td><td>"+result[2][index]+"</td></tr>");
			}
			
			
		}
		
	});
	
}


$(function(){
	loadFriendDetail();
	$("#friend-detail-container").css('height',frame);
	
	
	
	
	
	
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
		var showName=(friendVo.remark=='' ||  friendVo.remark==undefined)?
				friendVo.nickname:(friendVo.remark+"("+friendVo.nickname+")");
		$(frame).attr('src','chat_list.jsp?account='
				+params.account+"&portrait="+friendVo.portrait+"&showName="+showName);
	});
	
	
});