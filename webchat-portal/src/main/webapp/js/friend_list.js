
//方向标志，0表示左，1表示右
var flag=1;
$(function(){
	
	$("#friend-list-container").css('height',frame);
	
	$("#config-i").click(function(){
		if(flag==1){
			flag=0;
			$(this).toggleClass("fa-rotate-180");
			$("iframe").attr("src","friend_list_config.jsp");
			return;
		}
		if(flag==0){
			flag=1;
			$(this).toggleClass("fa-rotate-180");
			$("iframe").attr("src","");
			return;
		}
		
		
		
	});
	
	
	
	
	
	
	
});