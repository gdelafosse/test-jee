package test.jee.listener;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

@MessageDriven
public class MessageListenerBean implements MessageListener
{
    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name = "FooQueue")
    private Queue answerQueue;

    @Override
    public void onMessage(final Message message)
    {
        try
        {
            System.out.println(String.format("%s received message %s", this, message.getJMSMessageID()));
        }
        catch (JMSException e)
        {
            System.err.println(String.format("%s failed to read a message."));
        }
    }
}
