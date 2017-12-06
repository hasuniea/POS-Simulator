package org.wso2.iot.possimulator.websocket;

import javax.servlet.http.HttpServlet;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/request")
public class PaymentRequest extends HttpServlet {
    private Session session;

    @OnOpen
    public void connect(Session session) {
        this.session = session;
        System.out.println("Session"+session);

    }

    @OnClose
    public void close() {
        this.session = null;
        System.out.println("Closed");
    }

    @OnMessage
    public void onMessage(String msg) {
         this.session.getAsyncRemote().sendText("Echo"+msg);
    }
}
