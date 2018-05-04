<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<html>
<head>
    <title>Java后端WebSocket的Tomcat实现</title>
</head>
<body>
Welcome<br/><input id="text" type="text"/>
<button onclick="send">发送消息</button>
<hr />
<button onclick="closeWebSocket()">关闭WebSocket</button>
<hr />
<div id="message"></div>

<script type="text/javascript">
    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/websocket");
    } else {
        alert("当前浏览器 Not support websocket")
    }

    websocket.onerror = function() {
        setMessageInnerHTML("WebSocket连接发生错误！");
    };

    websocket.onopen = function() {
        setMessageInnerHTML("WebSocket连接成功");
    }

    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data)
    }

    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    window.onbeforeunload = function () {
        closeWebSocket();
    }

    function setMessageInnerHTML(innerHTML) {
        document.getElementById("message").innerHTML += innerHTML + '<br />';
    }

    function  closeWebSocket() {
        websocket.close();
    }

    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message)
    }
</script>
</body>
</html>