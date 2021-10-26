package an.test.springws.client.accessor;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="tests", url="http://localhost:9000")
public interface ServerClient {
    @RequestMapping(method = RequestMethod.GET, value = "/testing")
    String testMessage();
}

