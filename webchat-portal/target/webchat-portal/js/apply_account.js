
function getSpace(count){
	var space="";
	for (var i = 0; i < count; i++) {
		space+="&nbsp;";
	}
	return space;
	
}

function showInfo(msg,color){
	
	$("#info").html("<span style='color:"+color+";'>"+msg+"</span>");
}

function clearInfo(){
	
	$("#info").html("");
}
function clearInput(){
	$("#nickname").val("");
	$("#password").val("");
	$("#password2").val("");
	$("#email").val("");
}

$(function(){
	$("a").before(getSpace(14));
	
	
		$("#apply-btn").click(function(){
			//先清空
			clearInfo();
			//获取四个文本框的值
			var nickname=$("#nickname").val();
			var password=$("#password").val();
			var password2=$("#password2").val();
			var email=$("#email").val();
			//验证昵称，1<=length<=10
			if(nickname.length<1 || nickname.length>10){
				showInfo("昵称长度请保证在1到10个字符之间！","red");
				return;
			}
			
			
			//验证密码，6<=length<=15
			if(password.length<6 || password.length>15){
				showInfo("密码长度请保证在6到15个字符之间！","red");
				return;
			}
			
			
			//验证两次输入的密码是否相等
			if(password!=password2){
				showInfo("两次输入的密码不同！","red");
				return;
			}
			
			//验证邮箱
			var re=new RegExp("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$"); 
		    if (!re.test(email)) {
		    	showInfo("邮箱格式不正确！","red");
				return;
		    }
			
			
			
			$.ajax({
	        	type: "post",
	            dataType: "text",
	            url: ctx+"/applyAccount",
	            data: {
	            	"nickname":nickname,
	            	"password":password,
	            	"email":email
	            },
	            success:function (result) {
	            	if(result=="恭喜您,申请成功,邮件已发送至您的邮箱,请查收!"){
	            		showInfo(result,"green");
	            	}else{
	            		showInfo(result,"red");
	            	}
	        		//清空表单
	            	clearInput();
	            }
	    	});
			
			
		});
	});
