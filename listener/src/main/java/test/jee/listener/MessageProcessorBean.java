package test.jee.listener;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Stateless
public class MessageProcessorBean
{

    @EJB
    private MessageStoreBean messageStoreBean;

    @EJB
    private WebServiceClient webServiceClient;

    public void process(Message message)
    {
        try
        {
            if (message instanceof TextMessage)
            {
                System.out.println(String.format("APP - processing message %s", message.getJMSMessageID()));
                String modifiedMessage = webServiceClient.service(((TextMessage) message).getText());
                messageStoreBean.store((TextMessage)message, modifiedMessage);
            }
            else
            {
                throw new IllegalArgumentException("Expecting a TextMessage");
            }
        }
        catch (JMSException e)
        {
            throw new EJBException(e);
        }
    }
}
