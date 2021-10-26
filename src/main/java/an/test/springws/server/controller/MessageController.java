package an.test.springws.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static an.test.springws.server.config.WebsocketConfiguration.SAMPLE_ENDPOINT_MESSAGE_MAPPING;
import static an.test.springws.server.config.WebsocketConfiguration.WS_TOPIC_DESTINATION_PREFIX;

/**
 * @author Agustin Navcevich <agusnavce52@gmail.com>
 */
@RestController
public class MessageController {
    public static final String WS_TOPIC = WS_TOPIC_DESTINATION_PREFIX + SAMPLE_ENDPOINT_MESSAGE_MAPPING;

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(method = RequestMethod.GET, value = "/testing")
    public void test() {
        this.template.convertAndSend("/topic/test", "This is Send From Server");
    }

}
