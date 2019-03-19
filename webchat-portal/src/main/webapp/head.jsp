<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="./bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="./JQuery/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	var ctx="/webchat-portal";
	var fsr="http://localhost:81/";
	$(function(){
		$("img").attr('alt','加载失败');
		$("img").each(function(){
			var src=$(this).attr('src');
			$(this).attr('src',fsr+src+"?time="+new Date().getTime());
			
		});
	});
	
	function UrlSearch(params) {

		var str=location.href;
	   var num=str.indexOf("?");
	   str=str.substr(num+1);

	   var arr=str.split("&");
	   console.log(arr)
	   for(var i=0;i < arr.length;i++){
	        num=arr[i].indexOf("=");
	        if(num>0){
	             name=arr[i].substring(0,num);
	             value=arr[i].substr(num+1);
	             params[name]=value;
	        }
	   }
	} 
</script>