
function agreeApply(event,id){
	
	var ele=event.target;
	
	$.ajax({
		type:'post',
		url:ctx+'/agreeFriendApply',
		data:{
			id:id,
			addStatus:1
		},
		dataType:'json',
		success:function(result){
			if(result=='对方已成为您的好友！'){
				$(ele).prev().text("状态：已同意...");
				$(ele).next().remove();
				$(ele).remove();
			}
			alert(result);
		}
		
	});
}

function cancelApply(btn){
	
	$.ajax({
		type:'post',
		url:ctx+'/removeFriendApply',
		data:{
			userAccount:userObj.account,
			friendAccount:$(btn).prev().prev().prev().text(),
			type:"0"
		},
		dataType:'text',
		success:function(result){
			alert(result);
			window.location.href='friend_list_config.jsp';
		}
		
	});
	
}

function refuseApply(btn){
	
	$.ajax({
		type:'post',
		url:ctx+'/removeFriendApply',
		data:{
			userAccount:userObj.account,
			friendAccount:$(btn).prev().prev().prev().prev().text(),
			type:"1"
		},
		dataType:'text',
		success:function(result){
			alert(result);
			window.location.href='friend_list_config.jsp';
		}
		
	});
	
}








$(function(){
	
	$("#friend-list-config-container").css('height',frame);
	
	
	$("#query_applied").click(function(){
		if($(this).text()=='查看我收到的好友申请'){
			
			$.ajax({
				type:'post',
				url:ctx+'/queryFriendApplied',
				data:{
					friendAccount:userObj.account,
					addStatus:0
				},
				dataType:'json',
				success:function(result){
					if(result!=""){
						
						result=JSON.parse(result);
						for ( var index in result) {
							
							$("#applied_ul").append("<li><div style='margin-bottom:10px;'>账号:<a class='right-space-span' href='javascript:void(0)'>"
									+result[index].userAccount+"</a>验证信息:<span class='right-space-span'>"
									+result[index].validateInfo+"</span><span class='right-space-span'>状态:申请中...</span>" 
									+"<button class='btn-success right-space-span' onclick='agreeApply(event,"+result[index].id+")'>同意</button>"
									+"<button onclick='refuseApply(this)' class='btn-danger right-space-span'>拒绝</button></div></li>");
							
						}
					}else{
						$("#applied_ul").append("<li>暂无记录...</li>");
					}
					
					$("#applied_div").show();
					$("#query_applied").text('收起');
				}
				
			});
		}
		
		if($(this).text()=='收起'){
			$("#applied_div").hide();
			$("#applied_ul").html("");
			$("#query_applied").text('查看我收到的好友申请');
		}
		
	});
	$("#query_apply_byme").click(function(){
		if($(this).text()=='查看我发出的好友申请'){
			
			$.ajax({
				type:'post',
				url:ctx+'/queryFriendApplyByme',
				data:{
					userAccount:userObj.account,
					addStatus:0
				},
				dataType:'json',
				success:function(result){
					
					if(result!=""){
						
						
						result=JSON.parse(result);
						for ( var index in result) {
							
							$("#apply_byme_ul").append("<li><div style='margin-bottom:10px;'>账号:<a class='right-space-span' href='javascript:void(0)'>"
									+result[index].friendAccount+"</a>验证信息:<span class='right-space-span'>"
									+result[index].validateInfo+"</span><span class='right-space-span'>状态:申请中...</span>" 
									+"<button onclick='cancelApply(this)' class='btn-danger right-space-span'>取消申请</button></div></li>");
							
						}
						
					}else{
						$("#apply_byme_ul").append("<li>暂无记录...</li>");
					}
					
					$("#apply_byme_div").show();
					$("#query_apply_byme").text('收起');
				}
				
			});
		}
		
		if($(this).text()=='收起'){
			$("#apply_byme_div").hide();
			$("#apply_byme_ul").html("");
			$("#query_apply_byme").text('查看我发出的好友申请');
		}
		
	});
	
	
	$("#submit-btn").click(function(){
		//带有验证信息，添加好友
		$.ajax({
			type:'post',
			url:ctx+'/addFriendWithValidateInfo',
			data:{
				"userAccount":userObj.account,
				"friendAccount":$("input[name='friend-account']").val(),
				"userRemark":$("input[name='userRemark']").val(),
				"validateInfo":$("[name='validateInfo']").val()
			},
			dataType:'text',
			success:function(result){
				alert(result);
				$("#add-friend").show();
				$("input[name='friend-account']").removeAttr("disabled");
				$("#friend-apply-addition").hide();
			}
			
		});
		
		
	});
	
	$("#cancel-btn").click(function(){
		$("#add-friend").show();
		$("input[name='friend-account']").removeAttr("disabled");
		$("#friend-apply-addition").hide();
		
		
	});
	
	
	
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
					$("#add-friend").hide();
					$("input[name='friend-account']").attr("disabled","disabled");
					$("#friend-apply-addition").show();
				}else{
					alert(result);
				}
				
			}
			
		});
		
		
	});
	
	
	$("#search-btn").click(function(){
		
		var id=$("#search-input").val();
		if(id==''){
			parent.$("#friend-list-ul").children("li").each(function(){
				$(this).show();
			});
		}else{
			
			parent.$("#friend-list-ul").children("li").each(function(){
				if($(this).attr("id")!=("li-"+id)){
					$(this).hide();
				}
			});
		}
		
		
	});
	
});