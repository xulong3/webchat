


var flag=0;

function delLabel(i){
	var b=confirm('确认删除该标签?');
	if(b){
		
		var key=$(i).next().next().text();
		$.ajax({
			type: "post",
		    dataType: "text",
		    url: ctx+"/removeLabelByAccountAndKey",
		    data: {
		    	account:userObj.account,
		    	labelKey:key
		    },
		    success:function (result) {
		    	parent.window.refreshLabelCache(userObj.account,"1");
		    	window.location.href='lmc_personal.jsp';
		    	alert(result);
		    }
		});
	}
	
}


$(function(){
	//设置容器的高度
	$("#personal-container").css("height",frame);
	
	for ( var index in labelObj) {
		$("#label-ul").append("<li><i onclick='delLabel(this)' style='color: red;font-size: 20px;' class='fa fa-minus-square'></i>&nbsp;&nbsp;" +
				"<span><i style='color: green;font-size: 20px;' class='edit-i fa fa-pencil-square fa-2x'></i>&nbsp;&nbsp;</span>" +
				"<span><i style='color: green;font-size: 20px;' class='edit-finish-i fa fa-floppy-o fa-2x'></i>&nbsp;&nbsp;</span>" +
				"<span style='display: inline-block;width:200px;'>"+index+"</span><input class='value-input' " +
						"style='width: 450px;' readonly='readonly' " +
				"type='text' value='"+labelObj[index]+"'></li><br>");
	
	}
	
	$(".edit-i").each(function(){
		$(this).click(function(){
			var input=$(this).parent().next().next().next();
			
			input.removeClass('value-input');
			input.removeAttr('readonly');
			input.focus();
			
			$(this).parent().hide();
			$(this).parent().next().show();
		});
		
	});
	
	$("#save-i").hide();
	$("#new-key").hide();
	$("#new-value").hide();
	$(".edit-finish-i").parent().hide();
	
	$(".edit-finish-i").each(function(){
		$(this).click(function(){
			var key=$(this).parent().next().text();
			var value=$(this).parent().next().next().val();
			$.ajax({
				type: "post",
			    dataType: "text",
			    url: ctx+"/modifyLabelValue",
			    data: {
			    	account:userObj.account,
			    	labelValue:value,
			    	labelKey:key
			    },
			    success:function (result) {
			    	parent.window.refreshLabelCache(userObj.account,"1");
			    	window.location.href='lmc_personal.jsp';
			    	alert(result);
			    	
			    }
			});
		});
		
		
		
	});
	
	
	$("#add-i").click(function(){
		if(flag!=0){
			return;
		}
		$("#save-i").show();
		$("#new-key").show();
		$("#new-value").show();
		flag=1;
		
		
	});
	
	$("#save-i").click(function(){
		var	key=$("#new-key").val();
		var	value=$("#new-value").val();
		
		if(key=='' || value==''){
			alert('不能填写空白');
			return;
		}
		

		flag=0;
		$(this).hide();
		$("#new-key").hide();
		$("#new-value").hide();
		
		$.ajax({
			type: "post",
		    dataType: "text",
		    url: ctx+"/saveLabel",
		    data: {
		    	account:userObj.account,
		    	labelKey:key,
		    	labelValue:value
		    },
		    success:function (result) {
		    	parent.window.refreshLabelCache(userObj.account,"1");
		    	window.location.href='lmc_personal.jsp';
		    	alert(result);
		    }
		});
		
	});
});


