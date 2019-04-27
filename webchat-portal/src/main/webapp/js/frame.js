var sysLabelObj=parent.sysLabelObj;
var userObj=parent.userObj;
var labelObj=parent.labelObj;
var stompClient=parent.stompClient;
function sendMsg(receiver,msg,sendTime){
	parent.sendMsg(receiver,msg,sendTime);
}
//获取父页面iframe元素的高度
var frame=$("iframe",parent.document).css("height");