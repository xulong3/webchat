


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
	
	
	$("#portrait").change(function(){
		alert('ok');
		$.ajaxFileUpload({
            fileElementId: "portrait",    //需要上传的文件域的ID，即<input type="file">的ID。
            url: ctx+'/uploadPortrait', //后台方法的路径
            type: 'post',
            dataType: 'json',
            secureuri: false,//是否启用安全提交，默认为false。
            async : true,
            data:{
            	account:userObj.account
            },
            success: function(data) {
                
            },
            error: function(data, status, e) {
               
            }
        });
	});
	
	
	
	
});


