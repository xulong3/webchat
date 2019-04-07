var sysLabelObj=parent.sysLabelObj;
var userObj=parent.userObj;
var stompClient=parent.stompClient;
function sendMsg(receiver,msg){
	parent.sendMsg(receiver,msg);
}
//获取父页面iframe元素的高度
var frame=$("iframe",parent.document).css("height");