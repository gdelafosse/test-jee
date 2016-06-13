package test.jee.listener;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "TESTJEE")
public class MessageListenerBean implements MessageListener
{
    @EJB
    private MessageProcessorBean messageProcessorBean;

    @Override
    public void onMessage(final Message message)
    {
        messageProcessorBean.process(message);
    }
}
