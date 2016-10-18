package activemq.test.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 * Created by WANG on 16/10/13.
 */
public class NoPersistenceSender {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("MyTopic");
        MessageProducer producer = session.createProducer(destination);
        for (int i=0; i<3 ; i++) {
            TextMessage textMessage = session.createTextMessage("message==="+i);
            producer.send(textMessage);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
