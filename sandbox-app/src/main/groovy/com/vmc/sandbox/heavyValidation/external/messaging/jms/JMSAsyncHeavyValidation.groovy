package com.vmc.sandbox.heavyValidation.external.messaging.jms

import com.vmc.sandbox.heavyValidation.AsyncHeavyValidation
import com.vmc.sandbox.heavyValidation.external.config.HeavyValidationApplication
import com.vmc.sandbox.heavyValidation.external.messaging.MessageReceiver
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator

import javax.jms.JMSException
import javax.jms.Message
import javax.jms.Session

class JMSAsyncHeavyValidation implements AsyncHeavyValidation {

    private JmsTemplate jmsTemplate = JmsTemplate.smartNewFor(JMSAsyncHeavyValidation)

    @Override
    def doValidation(AsyncHeavyValidation.NotifyProgress notifyFunction) {
        MessageReceiver.addListener(notifyFunction)
        // Send a message
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("ping!");
            }
        };
        System.out.println("Sending a new message.");
        jmsTemplate.send(HeavyValidationApplication.MAIL_BOX, messageCreator);
    }

}
