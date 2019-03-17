<link href="./bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="./JQuery/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	var ctx="/webchat-portal";
	var fsr="http://localhost:81/";
	$(function(){
		$("img").attr('alt','加载失败');
		$("img").each(function(){
			var src=$(this).attr('src');
			$(this).attr('src',fsr+src);
			
		});
	});
	
	function UrlSearch(params) {
	   
	   var str=location.href; //取得整个地址栏
	   var num=str.indexOf("?")
	   str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]

	   var arr=str.split("&"); //各个参数放到数组里
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