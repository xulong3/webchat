
var stompClient = null;




function connect() {
	//连接sockjs的endpoint名称为/stubborn_webchat/endpointWisely
    var socket = new SockJS(ctx+'/endpointChat');
	//使用stomp子协议的websocket客户端
    stompClient = Stomp.over(socket);
	//连接websocket服务端
    stompClient.connect({}, function(frame) {
       
        console.log('Connected: ' + frame);
        /* 通过stompClient.subscribe订阅/topoc/getResponse目标（distination）发送的消息,
                                         这个是在控制器的@sendto中定义的 */
        stompClient.subscribe('/user/'+token+'/aabb', function(response){
            alert("接收到消息----"+response);
        });
    });
}
connect();

//通过stompClient.send向/welcome目标（distination）发送消息，这个是在控制器的@messageMapping中定义的
function sendMsg(receiver) {
    
    stompClient.send(
        "/chat", 
    	{}, 
    	JSON.stringify({ 
    		'sender': token,
    		'receiver':receiver,
    		'msg':'hello'	
    	})
    );
}
