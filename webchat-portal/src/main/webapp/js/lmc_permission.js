

$(function(){
	//设置容器的高度
	$("#permission-container").css("height",frame);
	
	//初始化配置信息
	$.ajax({
		type: "post",
	    dataType: "json",
	    url: ctx+"/queryConfigLabel",
	    data: {
	    	account:userObj.account
	    },
	    success:function (result) {
	    	var obj=$.parseJSON(result);
	    	
	    	$("#validate-way").val(obj.validateWay);
	    	$("#friend-circle").val(obj.friendCircle);
	    }
	});
	
	
	$("#validate-way").blur(function(){
		var configKey="validate_way";
		var configValue=$(this).val();
		var javaType="int";
		$.ajax({
			type: "post",
		    dataType: "json",
		    url: ctx+"/modifyConfigLabel",
		    data: {
		    	account:userObj.account,
		    	configKey:configKey,
		    	configValue:configValue,
		    	javaType:javaType
		    },
		    success:function (result) {
		    	if(result!=''){
		    		alert(result);
		    	}
		    }
		});
	});
	
	
	$("#friend-circle").blur(function(){
		var configKey="friend_circle";
		var configValue=$(this).val();
		var javaType="int";
		
		$.ajax({
			type: "post",
			dataType: "json",
			url: ctx+"/modifyConfigLabel",
			data: {
				account:userObj.account,
				configKey:configKey,
				configValue:configValue,
				javaType:javaType
			},
			success:function (result) {
				if(result!=''){
					alert(result);
				}
			}
		});
	});
	
	
	
	
});


