package activemq.test.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by WANG on 16/10/13.
 */
public class NoPersistenceReceiver {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean .TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("MyTopic");
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();
        while (message!=null) {
            TextMessage textMsg = (TextMessage) message;
            System.out.println("收到消息："+ textMsg.getText());
            message = consumer.receive(1000L);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
