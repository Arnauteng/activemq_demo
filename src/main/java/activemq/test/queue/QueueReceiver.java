package activemq.test.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by WANG on 16/10/13.
 */
public class QueueReceiver {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");
        MessageConsumer consumer = session.createConsumer(destination);
        for (int i=0; i<3 ; i++) {
            MapMessage message = (MapMessage) consumer.receive();
            System.out.println(message.getString("message---"+i)+", extra property="+message.getStringProperty
                    ("extra"));
//            if (i==1) {
//                session.commit();
//            }
        }
        session.commit();
        session.close();
        connection.close();
    }
}
