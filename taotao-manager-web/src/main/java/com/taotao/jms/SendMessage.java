package com.taotao.jms;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SendMessage {
    public static void sendQueueMessage(JmsTemplate jmsTemplate, ActiveMQTopic topic, final String msg){
        jmsTemplate.send(topic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage(msg);
                return message;
            }
        });
    }
}
