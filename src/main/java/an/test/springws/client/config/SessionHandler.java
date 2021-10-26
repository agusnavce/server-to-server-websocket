package an.test.springws.client.config;

import an.test.springws.server.controller.MessageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

/**
 * @author Agustin Navcevich <agusnavce52@gmail.com>
 */
@Component
public class SessionHandler extends StompSessionHandlerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(SessionHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        try {
            LOG.info("Subscribing....");
            this.subscribe(session);
        } catch(Exception e) {
            LOG.error("Error while sending data");
        }

    }

    protected void subscribe(StompSession session) {
        session.subscribe(MessageController.WS_TOPIC, this);
        LOG.info("Subscribed....");
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }

    public void handleFrame(StompHeaders headers, Object payload) {
        LOG.info("Client received: payload {}, headers {}", payload, headers);
    }
    @Override
    public void handleException(StompSession session, StompCommand command,
                                StompHeaders headers, byte[] payload, Throwable exception) {
        LOG.error("Client error: exception {}, command {}, payload {}, headers {}",
                exception.getMessage(), command, payload, headers);
    }
    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        LOG.error("Client transport error: error {}", exception.getMessage());
    }

}
