
var sysLabelObj=parent.sysLabelObj;

function initData(){
	
	//初始化数据
	for ( var key in sysLabelObj) {
		
		$("[name='"+key+"']").val(sysLabelObj[key]);
	}	
	//计算年龄和星座
	
}
$(function(){
	//设置容器的高度
	$("#sys-container").css("height",frame);
	
	
	
	$("#save-btn").click(function(){
		
	});
	
});


