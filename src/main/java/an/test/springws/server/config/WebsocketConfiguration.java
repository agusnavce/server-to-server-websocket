package an.test.springws.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author Agustin Navcevich <agusnavce52@gmail.com>
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    public static final String WS_ENDPOINT_PREFIX = "/app";

    public static final String WS_TOPIC_DESTINATION_PREFIX = "/topic";

    public static final String SAMPLE_ENDPOINT_MESSAGE_MAPPING = "/test";


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config.enableSimpleBroker(WS_TOPIC_DESTINATION_PREFIX);
        /*
           The prefix under which endpoints are available
         */
        config.setApplicationDestinationPrefixes(WS_ENDPOINT_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
        Here we register the single endpoints
         */
        registry.addEndpoint(SAMPLE_ENDPOINT_MESSAGE_MAPPING)
                .setAllowedOrigins("*")
                .withSockJS();

    }
}
