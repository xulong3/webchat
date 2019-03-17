
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
	
	$("#token").val("");
	$("#password").val("");
}

//0表示账号登录，1表示邮箱登录
var t=0;
function toggleToken(){
	clearInput();
	clearInfo();
	if(t==0){
		t=1;
		$("#login-way").text("使用账号登录");
		$("#way-span").text("邮箱");
	}else{
		t=0;
		$("#login-way").text("使用邮箱登录");
		$("#way-span").text("账号");
	}
}

$(function(){
	$("input[type='checkbox").before(getSpace(2));
	$("#to-reg").before(getSpace(14));
	$("#login-way").click(toggleToken);
	
	$("#login-btn").click(function(){
		clearInfo();
		
		var token=$("#token").val();
		var password=$("#password").val();
		//0表示未勾选，1表示勾选
		var rememberMe=0;
		if($("#rememberMe").is(":checked")){
			rememberMe=1;
		}
		
		
		if(t==0){
			//账号至少六位数，且纯数字
			var reg=/^[\d]+$/;
			if(token.length<6 || !reg.test(token)){
				showInfo("您输入的账号不合法，账号至少为六位数且为纯数字！","red");
				return;
			}
			
		}else{
			//验证邮箱格式
			var re=new RegExp("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$"); 
		    if (!re.test(token)) {
		    	showInfo("邮箱格式不正确！","red");
				return;
		    }
		}
		
		//验证密码，6<=length<=15
		if(password.length<6 || password.length>15){
			showInfo("您输入的密码不合法，密码长度请保证在6到15个字符之间！","red");
			return;
		}
		
		
		
		
		
		$.ajax({
        	type: "post",
            dataType: "text",
            url: ctx+"/login",
            data: {
            	"token":token,
            	"password":password,
            	"loginWay":t
            },
            success:function (result) {
            	
            	if(/^[\d]+$/.test(result)){
            		showInfo("登录成功！","green");
            		window.location.href="menu.jsp?token="+result;
            	}else{
            		showInfo(result,"red");
            	}
        		
            }
    	});
		
	});
});
