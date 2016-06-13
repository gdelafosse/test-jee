package test.jee.listener;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class MessageStoreBean
{
    @PersistenceContext(unitName = "test-jee-listener")
    private EntityManager entityManager;

    public void store(TextMessage message, String modifiedMessage) throws JMSException
    {
        System.out.println(String.format("APP - persisting message %s", message.getJMSMessageID()));
        MessageEntity toPersist = MessageEntity.from(message);
        toPersist.text = modifiedMessage;
        entityManager.persist(toPersist);
    }
}
