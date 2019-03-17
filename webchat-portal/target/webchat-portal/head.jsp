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
	
</script>