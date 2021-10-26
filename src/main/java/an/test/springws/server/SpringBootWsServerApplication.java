package an.test.springws.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author Agustin Navcevich <agusnavce52@gmail.com>
 */

@EnableWebSocket
@SpringBootApplication
public class SpringBootWsServerApplication {

    public static void main(final String[] args) {

        SpringApplication.run(SpringBootWsServerApplication.class, args);
    }


}
