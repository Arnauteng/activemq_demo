package activemq.test.broker;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

import java.net.URI;

/**
 * Created by WANG on 16/10/17.
 */
public class InnerBrokerFactory {
    public static void main (String[] args) throws Exception{
        String Uri = "properties:broker.properties";
        BrokerService broker1 = BrokerFactory.createBroker(new URI(Uri));
        broker1.addConnector("tcp://localhost:61616");
        broker1.start();
    }
}
