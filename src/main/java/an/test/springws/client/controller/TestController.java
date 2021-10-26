package an.test.springws.client.controller;

import an.test.springws.client.accessor.ServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private ServerClient serverClient;

    @Autowired
    public TestController(ServerClient serverClient) {
        this.serverClient = serverClient;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/testing")
    public void test() {
        serverClient.testMessage();
    }
}

