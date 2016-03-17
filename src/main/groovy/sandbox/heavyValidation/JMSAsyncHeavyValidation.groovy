package sandbox.heavyValidation

import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator
import sandbox.sandboxapp.external.interfaceAdapter.jms.MessageReceiver
import sandbox.sandboxapp.external.config.SandboxApplication

import javax.jms.JMSException
import javax.jms.Message
import javax.jms.Session

/**
 */
class JMSAsyncHeavyValidation implements AsyncHeavyValidation {

    private JmsTemplate jmsTemplate = SandboxApplication.jmsTemplate

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
        jmsTemplate.send(SandboxApplication.MAIL_BOX, messageCreator);
    }

}
