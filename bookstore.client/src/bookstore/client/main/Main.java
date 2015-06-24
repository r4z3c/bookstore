package bookstore.client.main;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {
	
	private void start(String[] args) throws NamingException, JMSException {		
		InitialContext context = new InitialContext();		
		Queue queue = (Queue) context.lookup("jms/queue/test");
		QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
		QueueConnection connection =  factory.createQueueConnection("usuarioRemoto","infnet123");
        QueueSession session = 
        connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        QueueSender sender = session.createSender(queue);
        
        ObjectMessage objectMessage = session.createObjectMessage("TESTE HUE");
        
        sender.send(objectMessage);
        
        sender = session.createSender(queue);
        
        sender.send(objectMessage);
	}

	public static void main(String[] args) throws NamingException, JMSException {
		new Main().start(args);
	}

}

//20:48:00,843 INFO  [org.jboss.as.messaging] (MSC service thread 1-1) JBAS011601: Bound messaging object to jndi name java:/topic/test
//20:48:00,847 INFO  [org.jboss.as.messaging] (MSC service thread 1-1) JBAS011601: Bound messaging object to jndi name java:jboss/exported/jms/topic/test
//20:48:00,883 INFO  [org.jboss.as.messaging] (MSC service thread 1-8) JBAS011601: Bound messaging object to jndi name java:jboss/exported/jms/RemoteConnectionFactory
//20:48:00,889 INFO  [org.jboss.as.messaging] (MSC service thread 1-8) JBAS011601: Bound messaging object to jndi name java:/RemoteConnectionFactory
//20:48:00,891 INFO  [org.jboss.as.messaging] (MSC service thread 1-3) JBAS011601: Bound messaging object to jndi name java:/ConnectionFactory
//20:48:01,303 INFORMAÇÕES [org.hornetq.core.server.impl.HornetQServerImpl] (MSC service thread 1-5) trying to deploy queue jms.queue.testQueue
//20:48:01,313 INFO  [org.jboss.as.deployment.connector] (MSC service thread 1-6) JBAS010406: Registered connection factory java:/JmsXA
//20:48:01,339 INFO  [org.jboss.as.messaging] (MSC service thread 1-5) JBAS011601: Bound messaging object to jndi name java:/queue/test
// 20:48:01,342 INFO  [org.jboss.as.messaging] (MSC service thread 1-5) JBAS011601: Bound messaging object to jndi name java:jboss/exported/jms/queue/test