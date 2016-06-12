package test.jee.listener;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MessageStoreBean
{
    @PersistenceContext(unitName = "test-jee-listener")
    private EntityManager entityManager;

    public void store(Message message) throws JMSException
    {
        if (message instanceof TextMessage)
        {
            System.out.println(String.format("persisting message %s", message.getJMSMessageID()));
            entityManager.persist(MessageEntity.from((TextMessage) message));
        }
        else
        {
            throw new IllegalArgumentException("Expecting a TextMessage");
        }
    }
}
