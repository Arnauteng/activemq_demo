package activemq.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by WANG on 16/10/17.
 */
@Component
public class SpringQueueSender {
    @Resource
    private JmsTemplate jt;

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringQueueSender queueSender = (SpringQueueSender) ctx.getBean("springQueueSender");
        System.out.println("sending");
        queueSender.jt.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage("Spring msg sent===");
                return message;
            }
        });
        System.out.println("ending");
    }
}
