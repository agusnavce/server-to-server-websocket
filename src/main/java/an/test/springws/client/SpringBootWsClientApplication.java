package an.test.springws.client;

import an.test.springws.client.config.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Agustin Navcevich <agusnavce52@gmail.com>
 */
@EnableWebSocket
@SpringBootApplication
@EnableFeignClients
public class SpringBootWsClientApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SpringBootWsClientApplication.class);

    @Autowired
    SessionHandler sessionHandler;

    @Autowired
    WebSocketStompClient stompClient;

    private ListenableFuture<StompSession> session;

    // initialize after application startup
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ScheduledExecutorService jobScheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable scanTask = () -> {
            monitorConnection();
        };
        jobScheduler.scheduleWithFixedDelay(scanTask, 2, 20, TimeUnit.SECONDS);
    }

    private void monitorConnection() {
        LOG.info("Monitoring....");
        if (session == null || session.isCancelled()) {
            LOG.info("Connection starting....");
            this.session = stompClient.connect("ws://localhost:9000/test", sessionHandler);
            LOG.info("Connection started....");
        }
    }

    public static void main(final String[] args) {
        SpringApplication.run(SpringBootWsClientApplication.class, args);
    }



}
