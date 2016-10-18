package activemq.test.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * Created by WANG on 16/10/17.
 */
public class InnerBroker {
    public static void main (String[] args) throws Exception{
        BrokerService broker = new BrokerService();

        broker.setUseJmx(true);

        broker.addConnector("tcp://localhost:61616");

        broker.start();

    }
}
