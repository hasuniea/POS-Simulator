
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
</head>
<body class="signup-page">
<h1>Payment</h1>
<script>
    var webSocket = new WebSocket("ws://localhost:8080/request");
    webSocket.onmessage = function () {
        alert(event.data);
    }
</script>
<button onclick="webSocket.send('Message')">send</button>

</body>
</html>
