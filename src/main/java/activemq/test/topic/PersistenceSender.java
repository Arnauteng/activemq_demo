package activemq.test.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by WANG on 16/10/13.
 */
public class PersistenceSender {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

        Topic destination = session .createTopic("MyTopic9");

        MessageProducer producer = session.createProducer(destination);

        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        connection.start();

        for (int i=0; i<3 ; i++) {
            TextMessage textMessage = session.createTextMessage("message888==="+i);
            producer.send(textMessage);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
