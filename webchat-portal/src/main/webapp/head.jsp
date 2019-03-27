<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="./bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="./font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet">
<link href="./bootstrap-switch/css/bootstrap-switch.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="./JQuery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="./bootstrap-treeview/js/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="./bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="./My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="./websocket/sockjs.min.js"></script>
<script type="text/javascript" src="./websocket/stomp.min.js"></script>

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
	
	function getSpace(count){
		var space="";
		for (var i = 0; i < count; i++) {
			space+="&nbsp;";
		}
		return space;
		
	}
	
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
	
	//btn为按钮对象，size可为字符串类型，lg,2x,3x,4x,5x
	function btnDisabled(btn,size){
		$(btn).attr("disabled",true);
		$(btn).css("background-color","gray");
		$(btn).css("border","none");
		$(btn).html("<i class='fa fa-spinner fa-spin fa-"+size+"'></i>加载中...");
	}
	
	
	function btnEnable(btn,content){
		$(btn).removeAttr("disabled");
		$(btn).css("background-color","");
		$(btn).css("border","");
		$(btn).html(content);
	}
	
	
	
	
	
</script>