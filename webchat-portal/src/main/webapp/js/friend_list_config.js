


$(function(){
	
	$("#friend-list-config-container").css('height',frame);
	
	
	$("#add-friend").click(function(){
		var account=$("input[name='friend-account']").val();
		//查看该账号是否是有效账号
		var reg=/^\d{6,}$/;
		if(account=='' || !reg.test(account)){
			alert("请输入有效的账号");
			return;
		}
		//添加好友
		$.ajax({
			type:'post',
			url:ctx+'/addFriend',
			data:{
				"account":userObj.account,
				"friendAccount":account
			},
			dataType:'text',
			success:function(result){
				if(result=='需要验证信息'){
					
				}else{
					alert(result);
				}
				
			}
			
		});
		
		
	});
	
});