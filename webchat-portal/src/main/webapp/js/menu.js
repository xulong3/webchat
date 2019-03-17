
//获取参数token
var params={};
UrlSearch(params);
console.log(params.token);
var token=params.token;
var userObj={};
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

$(function(){
	
	$("#menu-container").css('width',$(window).width());
	$("#menu-container").css('height',$(window).height());
	
	//
	$("#nickname-a").text(userObj.nickname);
	
	
});