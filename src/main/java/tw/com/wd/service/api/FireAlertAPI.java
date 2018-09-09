package tw.com.wd.service.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tw.com.wd.service.obj.Message;

@RestController
public class FireAlertAPI {
    @RequestMapping(path = "/hello", method = RequestMethod.POST)
    public Message sayHello(@RequestBody Message message) {
        System.out.println("Say hello to " + message.getContent());
        return new Message(1, "Hello, stranger");
    }

    @RequestMapping(path = "/message", method = RequestMethod.POST)
    public Message sendMessage(@RequestBody String reqMessage) {
        System.out.printf("Req: %s\n", reqMessage);
        return new Message(1, "received");
    }
}
