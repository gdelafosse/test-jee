package test.jee.listener;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "TESTJEE")
public class MessageListenerBean implements MessageListener
{
    @EJB
    private MessageStoreBean messageStore;

    @Override
    public void onMessage(final Message message)
    {
        try
        {
            messageStore.store(message);
        }
        catch (JMSException e)
        {
            System.err.println(String.format("%s failed to read a message."));
        }
    }
}
