package bookstore.ejb.queues;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Remote;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import bookstore.core.models.IOrder;

@MessageDriven(activationConfig = {  
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),  
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "/queue/order")  
})
@Remote
public class Order implements MessageListener {

    public Order() {
    }

	@Override
	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = null;
			objectMessage = (ObjectMessage) message;
			IOrder order = (IOrder) objectMessage.getObject();
			
			System.out.println("---------------------------------------------------------------");
			System.out.println(order.toString());
			System.out.println("---------------------------------------------------------------");
		} catch (JMSException e) {
			e.printStackTrace();
		} 
	}
}
