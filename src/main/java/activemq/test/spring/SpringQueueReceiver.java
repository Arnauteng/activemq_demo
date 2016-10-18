package activemq.test.spring;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by WANG on 16/10/13.
 */
@Component
public class SpringQueueReceiver {
    @Resource
    private JmsTemplate jt;

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringQueueReceiver queueReceiver = (SpringQueueReceiver) ctx.getBean("springQueueReceiver");
        String msg = (String) queueReceiver.jt.receiveAndConvert();
        System.out.println("收到："+msg);
    }
}
