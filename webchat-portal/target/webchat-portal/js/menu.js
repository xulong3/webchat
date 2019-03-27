
//获取参数token
var params={};
UrlSearch(params);
console.log(params.token);
var token=params.token;
var userObj={};
var labelObj={};
var sysLabelObj={};
//根据token从redis缓存查找用户基本信息
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


//根据token从redis缓存查找用户标签信息
$.ajax({
	type: "post",
    dataType: "json",
    url: ctx+"/getLabelCache",
    async:false,
    data: {
    	"token":token
    },
    success:function (result) {
    	labelObj=$.parseJSON(result);
    	
    }
});

//根据token从redis缓存查找用户标签信息
$.ajax({
	type: "post",
    dataType: "json",
    url: ctx+"/getSysLabelCache",
    async:false,
    data: {
    	"token":token
    },
    success:function (result) {
    	sysLabelObj=$.parseJSON(result);
    	console.log(sysLabelObj.portrait);
    }
});



function init(){
	//设置头像
	$("#portrait").attr("src",fsr+sysLabelObj.portrait+"?time="+new Date().getTime());
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
	
	
});