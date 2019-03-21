
//获取参数token
var params={};
UrlSearch(params);
console.log(params.token);
var token=params.token;
var userObj={};
var labelObj={};
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
    	console.log(labelObj.头像);
    }
});



function init(){
	//设置头像
	$("#portrait").attr("src",fsr+labelObj.头像+"?time="+new Date().getTime());
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