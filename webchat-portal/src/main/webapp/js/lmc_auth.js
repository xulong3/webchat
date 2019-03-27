var userObj=parent.userObj;

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
});


