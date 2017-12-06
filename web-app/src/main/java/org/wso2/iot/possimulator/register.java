package org.wso2.iot.possimulator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "/register")
public class register extends HttpServlet {

    private static Log log = LogFactory.getLog(register.class);
    private String DEVICE_TYPE = "POS";
    private String broker = "tcp://localhost:1883";
    private Boolean subscriber = true;

    protected void doPost(HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false);
        String token =session.getAttribute("accessToken").toString();
        String ID = req.getParameter("posId");
        out.print("token "+ token + "Device ID"+ ID);

        String clientId = ID + ":" + DEVICE_TYPE;
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient sampleClient = null;
        try {
            sampleClient = new MqttClient(broker, clientId, persistence);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setUserName(token);
        connOpts.setPassword("".toCharArray());
        connOpts.setKeepAliveInterval(120);
        connOpts.setCleanSession(true);

        try {
            sampleClient.connect(connOpts);
            log.info("Connecting to broker: " + broker);
            out.print("Connecting to broker: " + broker);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        final String myTopic = Constant.topic+ID+ "/operation/#";
        // subscribe to topic if subscriber
        if (subscriber) {
            try {
                int subQoS = 0;
                sampleClient.subscribe(myTopic, subQoS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sampleClient.setCallback(new MqttCallback() {

            @Override
            public void connectionLost(Throwable cause) { //Called when the client lost the connection to the broker
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println(topic + ": " + message);
                
                out.println("<script language=javascript");
                out.println("alert \'you have got messages\'");
                out.println("</script>");

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {//Called when a outgoing publish is complete
            }
        });
       req.getRequestDispatcher("/payment.jsp").forward(req, res);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
