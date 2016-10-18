package activemq.test.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 * Created by WANG on 16/10/13.
 */
public class QueueSender {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Enumeration names = connection.getMetaData().getJMSXPropertyNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            System.out.println("jms name==="+name);
        }

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");
        MessageProducer producer = session.createProducer(destination);
        for (int i=0; i<3 ; i++) {
            MapMessage message = session.createMapMessage();
            message.setStringProperty("extra", "ok");
            message.setString("message---"+i,"my map message=="+i);
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
