
//获取参数token
var params={};
UrlSearch(params);
console.log(params.token);
var token=params.token;
var userObj={};
var labelObj={};
var sysLabelObj={};
//根据token从redis缓存查找用户基本信息
function loadAuchCache(){
	
	$.ajax({
		type: "post",
		dataType: "json",
		url: ctx+"/getUserCache",
		async:false,
		data: {
			"token":token
		},
		success:function (result) {
			userObj=$.parseJSON(result);
			console.log(userObj.nickname);
		}
		
	});
}

function refreshSysLabelCache(account,isClear){
	
	//根据token从redis缓存查找用户标签信息
	$.ajax({
		type: "post",
		dataType: "json",
		url: ctx+"/getSysLabelCache",
		async:false,
		data: {
			"token":account,
			//是否先清除缓存
			"isClear":isClear
		},
		success:function (result) {
			sysLabelObj=$.parseJSON(result);
			
		}
	});
}

function refreshLabelCache(account,isClear){
	
	//根据token从redis缓存查找用户标签信息
	$.ajax({
		type: "post",
		dataType: "json",
		url: ctx+"/getLabelCache",
		async:false,
		data: {
			"token":account,
			"isClear":isClear
		},
		success:function (result) {
			if(result==undefined || result==''){
				return;
			}
			labelObj=$.parseJSON(result);
			
		}
	});
}


function refreshPortrait(){
	//设置头像
	$("#portrait").attr("src",fsr+sysLabelObj.portrait+"?time="+new Date().getTime());
}



loadAuchCache();
refreshLabelCache(token,"0");
refreshSysLabelCache(token,"0");


function init(){
	refreshPortrait();
	
	//获取用户名
	$("#nickname-a").text(userObj.nickname);
	$("#nickname-a").click(function(){
		
		$("iframe").attr("src","lmc_auth.jsp");
	});
}

$(function(){
	
	$("#menu-container").css('width',$(window).width());
	$("#menu-container").css('height',$(window).height());
	init();
	$(".menu-item").each(function(){
		$(this).after(getSpace(5));
		$(this).click(function(){
			if($(this).text()=='好友列表'){
				
				$("iframe").attr("src","friend_list.jsp");
			}
			if($(this).text()=='会话列表'){
				
				$("iframe").attr("src","chat_list.jsp");
			}
			
			if($(this).text()=='朋友圈'){
				
				$("iframe").attr("src","friend_circle.jsp");
			}
			
		});
	});
	
	
	
	
});