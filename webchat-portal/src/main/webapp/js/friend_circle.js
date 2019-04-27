//0表示当前是全部动态，1表示当前是我的动态
var flag=0;

function openComment(a){
	$(a).parent().next().removeClass('hide');
	
	
}
function delDynamic(a){
	var b=confirm('确认删除?');
	if(!b){
		return;
	}
	
	$.ajax({
		type: "post",
		dataType: "text",
		url: ctx+"/removeFriendDynamicByDynamicId",
		data: {
			"dynamicId":$(a).parent().prev().prev().children(":first").val()
		},
		success:function (result) {
			alert(result);
			if(flag==0){
				$("#query-all-btn").trigger('click');
			}else{
				$("#query-btn").trigger('click');
			}
		}
	});
	
	
}
function queryComment(a){
	
	$.ajax({
		type: "post",
		dataType: "json",
		url: ctx+"/queryFriendDynamicCommentByDynamicId",
		data: {
			"dynamicId":$(a).parent().next().next().children(":first").val()
		},
		success:function (result) {
			$(a).parent().next().children(":first").html('');
			if(result=='' || result=='[]'){
				$(a).parent().next().children(":first").append("没有任何评论!");
				
				return;
			}
			
			
			result=JSON.parse(result);
			for ( var index in result) {
				var str="<li><a href='javascript:void(0)'>"+result[index].nickname+"</a>:&nbsp;&nbsp;"+result[index].commentContent+"</li>";
				$(a).parent().next().children(":first").append(str);
			}
			
			
			
			
		}
	});
	
}
function saveComment(btn){
	
	var content=$(btn).prev().val();
	if(content==''){
		alert('评论内容不能为空!')
		return;
	}
	
	$.ajax({
		type: "post",
		dataType: "text",
		url: ctx+"/saveComment",
		data: {
			"commentAccount":userObj.account,
			"dynamicId":$(btn).parent().prev().children(":first").val(),
			"commentContent":content
		},
		success:function (result) {
			alert(result);
			$(btn).prev().val('');
			$(btn).parent().addClass('hide');
		}
	});
	
}
function savePraise(a){
	
	$.ajax({
		type: "post",
		dataType: "text",
		url: ctx+"/addPraise",
		data: {
			"praiseAccount":userObj.account,
			"dynamicId":$(a).prev().val()
		},
		success:function (result) {
			$(a).next().show();
			$(a).hide();
			
			$(a).parent().prev().prev().children(":eq(1)").text(parseInt($(a).parent().prev().prev().children(":eq(1)").text())+1);
		}
	});
}
function cancelPraise(a){
	
	$.ajax({
		type: "post",
		dataType: "text",
		url: ctx+"/removePraise",
		data: {
			"praiseAccount":userObj.account,
			"dynamicId":$(a).prev().prev().val()
		},
		success:function (result) {
			$(a).prev().show();
			$(a).hide();
			
			$(a).parent().prev().prev().children(":eq(1)").text(parseInt($(a).parent().prev().prev().children(":eq(1)").text())-1);
		}
	});
}

//参数含义：showPraise:0表示不显示，则表示是我的动态
function addDynamic(portrait,nickname,textContent,praiseCount,publishTime,showPraise,dynamicId){
	$("#dynamic-ul").append("<li class='dynamic-li'>"
			+"<div class='img-div'>"
				+"<img alt='加载失败' src='http://localhost:81/"+portrait+"' class='dynamic-img'>"
			+"</div>"
			+"<div class='content-div'>"
				+"<div class='row name-row'>"
					+"<a href='javascript:void(0)' class='name-size'>"+nickname+"</a>"
				+"</div>"
				+"<div class='row text-row'>"
					+"<span>"+textContent+"</span>"
				+"</div>"
				+"<div class='row praise-row'>"
					+"<a href='javascript:void(0)'>点赞量:</a>"
					+"<a href='javascript:void(0)'>"+praiseCount+"</a>"
					+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a href='javascript:void(0)'>评论区:</a>&nbsp;&nbsp;"
					+"<a href='javascript:void(0)' onclick='queryComment(this)'>展开</a>"
					+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a href='javascript:void(0)'>发表时间:</a>&nbsp;&nbsp;"
					+"<a href='javascript:void(0)'>"+publishTime+"</a>"
				+"</div>"
				
				+"<div class='row'><ul style='list-style:none;padding:0;'>"
				
				+"</ul></div>"
				
				+"<div class='row oper-row'>"
					+"<input class='hide' type='text' value='"+dynamicId+"'>"
					+"<a class='savePraise' href='javascript:void(0)' onclick='savePraise(this)'><i class='fa fa-thumbs-o-up' style='font-size:20px;'></i></a>&nbsp;&nbsp;"
					+"<a class='cancelPraise' href='javascript:void(0)' onclick='cancelPraise(this)'><i class='fa fa-thumbs-up' style='font-size:20px;'></i></a>&nbsp;&nbsp;"
					+"<a href='javascript:void(0)' onclick='openComment(this)'><i class='fa fa-commenting-o' style='font-size:20px;'></i></a>"
					
				+"</div>"
				+"<div class='row hide'>"
					+"<textarea style='width: 100%;height: 60px;resize: none;'></textarea>"
					+"<button class='btn-success' onclick='saveComment(this)'>提交评论</button>"
				+"</div>"
				
				+"<div class='row hide del-dynamic'>"
					+"<a onclick='delDynamic(this)' href='javascript:void(0)' style='color:red;'>删除此条动态</a>"
				+"</div>"
			+"</div>"
			+"<div style='clear: both;'></div>"	
		+"</li>");
	
	if(showPraise=='0'){
		
		$(".oper-row:last").hide();
		$(".del-dynamic:last").removeClass('hide');
	}else{
		//查看我是否点过了赞过了这条动态
		$.ajax({
			type: "post",
			dataType: "text",
			url: ctx+"/isPraise",
			async:false,
			data: {
				"praiseAccount":userObj.account,
				"dynamicId":dynamicId
			},
			success:function (result) {
				
				if(result=='true'){
					$(".savePraise:last").hide();
				}
				if(result=='false'){
					$(".cancelPraise:last").hide();
				}
			}
		});
		
		
	}
	
	
}

function loadDynamic(){
	flag=0;
	$.ajax({
		type: "post",
		dataType: "json",
		url: ctx+"/queryFriendDynamic",
		data: {
			"account":userObj.account
		},
		success:function (result) {
			
			$("#dynamic-ul").html('');
			$("#dynamic-ul").append("</li><h2 class='my-dynamic'>全部动态</h2></li>");
			
			
			if(result=='[]' || result==''){
				
				$("#dynamic-ul").append("</li>没有任何动态!</li>");
				return;
			}
			result=JSON.parse(result);
			
			for ( var index in result) {
				var showName=(result[index].remark==undefined || result[index].remark=='')?
						result[index].nickname:result[index].remark;
						
				if(result[index].account==userObj.account){
					
					addDynamic(result[index].portrait,showName,result[index].textContent,
							result[index].praiseCount,result[index].publishTime,0,result[index].dynamicId);
				}else{
					addDynamic(result[index].portrait,showName,result[index].textContent,
							result[index].praiseCount,result[index].publishTime,1,result[index].dynamicId);
				}
			}
		}
	});
}





$(function(){
	
	$("#friend-circle-container").css('height',frame);
	loadDynamic();
	$("#publish-btn").click(function(){
		
		var content=$("#dynamic-textarea").val();
		if(content==''){
			alert('内容不能为空');
			return;
		}
		
		$.ajax({
			type: "post",
			dataType: "text",
			url: ctx+"/saveFriendDynamic",
			data: {
				"account":userObj.account,
				"textContent":content
			},
			success:function (result) {
				$("#dynamic-textarea").val('')
				alert(result);
				if(flag==0){
					$("#query-all-btn").trigger('click');
				}else{
					$("#query-btn").trigger('click');
				}
			}
		});
	});
	
	$("#query-btn").click(function(){
		
		flag=1;
		
		$.ajax({
			type: "post",
			dataType: "json",
			url: ctx+"/queryFriendDynamicSelf",
			data: {
				"account":userObj.account
			},
			success:function (result) {
				$("#dynamic-ul").html('');
				$("#dynamic-ul").append("</li><h2 class='my-dynamic'>我的动态</h2></li>");
				if(result=='[]' || result==''){
					$("#dynamic-ul").append("</li>没有任何动态!</li>");
					return;
				}
				result=JSON.parse(result);
				
				for ( var index in result) {
					addDynamic(result[index].portrait,result[index].nickname,result[index].textContent,
							result[index].praiseCount,result[index].publishTime,0,result[index].dynamicId);
				}/*for循环结束*/
			}/*success:function*/
		});/*ajax*/
		
		
		
	});
	
	
	$("#query-all-btn").click(loadDynamic);
	
	
	
	
	
	
	
	
});