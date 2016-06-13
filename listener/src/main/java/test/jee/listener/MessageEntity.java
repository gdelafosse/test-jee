package test.jee.listener;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MESSAGES")
public class MessageEntity {

    @Id
    public String messageID;

    public String text;
    public String correlationID;
    public int deliveryMode;
    public String destination;
    public long expiration;

    public long timeStamp;
    public int priority;
    public String replyTo;
    public String type;
    public boolean redelivered;

    public static MessageEntity from(TextMessage message) throws JMSException
    {
        MessageEntity entity = new MessageEntity();
        entity.correlationID = message.getJMSCorrelationID();
        entity.deliveryMode = message.getJMSDeliveryMode();
        entity.destination = String.valueOf(message.getJMSDestination());
        entity.expiration = message.getJMSExpiration();
        entity.messageID = message.getJMSMessageID();
        entity.timeStamp = message.getJMSTimestamp();
        entity.priority = message.getJMSPriority();
        entity.replyTo = String.valueOf(message.getJMSReplyTo());
        entity.type = message.getJMSType();
        entity.redelivered = message.getJMSRedelivered();
        entity.text = message.getText();
        return entity;
    }
}
