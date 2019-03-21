
$(function(){
	//设置容器的高度
	$("#auth-container").css("height",frame);
	//初始化bootstrap-switch
	$("#sw").bootstrapSwitch({  
    	onText : "on", 
        offText : "off",
        onColor : "success",
        offColor : "default", 
        size : "null",  
        handleWidth:"30",
		labelWidth:"20",
		animate:false,
		inverse:true
    });  
		
	
	
});


