package test.jee.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class Producer
{

    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name = "TESTJEE")
    private Queue queue;

    private Connection connection;

    @PostConstruct
    private void postConstruct() throws JMSException
    {
        connection = connectionFactory.createConnection();
        connection.start();
    }

    @PreDestroy
    private void preDestroy() throws JMSException
    {
        if (connection != null)
        {
            connection.close();
        }

    }

    public void sendMessage(String message) throws JMSException
    {
        Session session = null;

        try
        {
            // Create a Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(queue);

            // Create a message
            TextMessage textMessage = session.createTextMessage(message);

            // Tell the producer to send the message
            producer.send(textMessage);
        }
        finally
        {
            // Clean up
            if (session != null)
            {
                session.close();
            }
        }
    }
}
