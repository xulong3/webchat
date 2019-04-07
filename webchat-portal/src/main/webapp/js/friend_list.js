
var friendVoList={};


function loadFriendInfo(friendAccount){
	
	$("iframe").attr("src","friend_detail.jsp?account="+friendAccount);
}



//方向标志，0表示左，1表示右
var flag=1;
$(function(){
	
	$("#friend-list-container").css('height',frame);
	
	$("#config-i").click(function(){
		if(flag==1){
			flag=0;
			$(this).toggleClass("fa-rotate-180");
			$("iframe").attr("src","friend_list_config.jsp");
			return;
		}
		if(flag==0){
			flag=1;
			$(this).toggleClass("fa-rotate-180");
			$("iframe").attr("src","");
			return;
		}
		
		
		
	});
	
	
	//加载好友信息
	$.ajax({
		type:'post',
		url:ctx+'/loadFriends',
		data:{
			userAccount:userObj.account,
			addStatus:1
		},
		dataType:'json',
		success:function(result){
			result=JSON.parse(result);
			for ( var index in result) {
				friendVoList[result[index].account]=result[index];
				
				var showName=result[index].showName==''?result[index].nickname:(result[index].showName+"("+result[index].nickname+")");
				
				$("#friend-list-ul").append("<li class='friend-li'>" 
						+"<img class='friend-img' src='http://localhost:81"+result[index].portrait+"'>"
						+"<div class='friend-info'><a href='javascript:void(0)' onclick='loadFriendInfo("+result[index].account+")'>"
						+showName
						+"</a></div></li>");
				
				
			}
			
		}
		
	});
	
	
	
	
	
	
});