package tw.com.wd.service.api;

import org.springframework.web.bind.annotation.*;
import tw.com.wd.service.obj.Message;
import tw.com.wd.service.util.SignatureUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@RestController
public class FireAlertAPI {
    @RequestMapping(path = "/hello", method = RequestMethod.POST)
    public Message sayHello(@RequestBody Message message) {
        System.out.println("Say hello to " + message.getContent());
        return new Message(1, "Hello, stranger");
    }

    @RequestMapping(path = "/message", method = RequestMethod.POST)
    public Message sendMessage(@RequestHeader(name = "x-line-signature") String signature, @RequestBody String reqMessage) {

        System.out.printf("Req: %s\n", reqMessage);
        if (SignatureUtil.isValid(signature, reqMessage)) {
            return new Message(1, "received");
        } else {
            return new Message(0, "Wrong signature");
        }
    }
}
