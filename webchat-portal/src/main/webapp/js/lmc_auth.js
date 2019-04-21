

$(function(){
	
	//设置容器的高度
	$("#auth-container").css("height",frame);
	//初始化bootstrap-switch
	/*$("#sw").bootstrapSwitch({  
    	onText : "on", 
        offText : "off",
        onColor : "success",
        offColor : "default", 
        size : "null",  
        handleWidth:"30",
		labelWidth:"20",
		animate:false,
		inverse:true
    });  */
		
	$("#nickname-save").hide();
	$("#email-save").hide();
	$("#update-pwd-span").after(getSpace(10));
	
	$("#account").val(userObj.account);
	$("#nickname").val(userObj.nickname);
	$("#email").val(userObj.email);
	
	$("#edit-i").click(function(){
		var i=$("#pwd-row").attr("closed");
		if(i=='true'){
			$("#pwd-row").show();
			$("#pwd-row").attr("closed","false");
		}else{
			$("#pwd-row").hide();
			$("#pwd-row").attr("closed","true");
		}
	});
	
	
	$("#email-i").click(function(){
		$("#email").removeAttr('disabled');
		$("#email-i").hide();
		$("#email-save").show();
	});
	$("#email-save").click(function(){
		$("#email").attr('disabled','disabled');
		$("#email-i").show();
		$("#email-save").hide();
		var email=$("#email").val();
		
		$.ajax({
			type:'post',
			url:ctx+'/sendEmailToOldEmail',
			data:{
				account:userObj.account,
				oldEmail:userObj.email,
				newEmail:email
			},
			dataType:'text',
			success:function(result){
				alert(result);
				
				
			}
			
		});
		
		
	});
	
	$("#nickname-i").click(function(){
		$("#nickname").removeAttr('disabled');
		$("#nickname-i").hide();
		$("#nickname-save").show();
		
		
		
	});
	$("#nickname-save").click(function(){
		$("#nickname").attr('disabled','disabled');
		$("#nickname-i").show();
		$("#nickname-save").hide();
		var nickname=$("#nickname").val();
		
		$.ajax({
			type:'post',
			url:ctx+'/modifyUserNickname',
			data:{
				account:userObj.account,
				nickname:nickname
			},
			dataType:'text',
			success:function(result){
				alert(result);
				if(result=='修改成功！'){
					//清楚auth缓存
					$.ajax({
						type:'post',
						url:ctx+'/clearAuthCache',
						data:{
							account:userObj.account
						},
						dataType:'text',
						success:function(result){
							parent.userObj={};
							parent.loadAuchCache();
							userObj=parent.userObj;
						}
						
					});
				}
			}
			
		});
		
	});
	
	
	$("#pwd-save").click(function(){
		var oldPwd=$("#old-pwd").val();
		var newPwd=$("#new-pwd").val();
		var newPwd_2=$("#new-pwd-2").val();
		
		if(oldPwd=='' || newPwd_2=='' || newPwd==''){
			return;
		}
		
		
		
		if(newPwd!=newPwd_2){
			alert("两次输入的新密码不一致");
			return;
		}
		
		$.ajax({
			type:'post',
			url:ctx+'/modifyUserPwd',
			data:{
				account:userObj.account,
				oldPwd:oldPwd,
				newPwd:newPwd
			},
			dataType:'text',
			success:function(result){
				alert(result);
				if(result=='密码修改成功！请您重新登录!'){
					$("#pwd-row").hide();
					$("#pwd-row").attr("closed","true");
					
					$("#old-pwd").val('');
					$("#new-pwd-2").val('');
					$("#new-pwd").val('');

					parent.window.location.href="http://localhost:8081";
				}
			}
			
		});
		
		
	});
	
	
	
	
});


