package activemq.test.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by WANG on 16/10/18.
 */
public class MyMessageListener implements MessageListener{
    public void onMessage(Message message) {
        TextMessage txtMsg = (TextMessage)message;
        try {
            System.out.println("receive txt msg ==="+((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
