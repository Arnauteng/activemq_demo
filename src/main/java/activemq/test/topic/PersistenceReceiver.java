package activemq.test.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by WANG on 16/10/13.
 */
public class PersistenceReceiver {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        Connection connection = connectionFactory.createConnection();

        connection.setClientID("cc1");

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

        Topic destination = session.createTopic("MyTopic9");

        TopicSubscriber topicSubscriber = session.createDurableSubscriber(destination, "t1");

        connection.start();

        Message message = topicSubscriber.receive();
        while (message!=null) {
            TextMessage textMsg = (TextMessage) message;
            System.out.println("收到消息："+ textMsg.getText());
            message = topicSubscriber.receive(1000L);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
