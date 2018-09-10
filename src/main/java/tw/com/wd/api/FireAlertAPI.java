package tw.com.wd.api;

import org.springframework.web.bind.annotation.*;
import tw.com.wd.handler.FireAlertHandlerChain;
import tw.com.wd.handler.LineEventHandlerChain;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.obj.Message;
import tw.com.wd.util.SignatureUtil;

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
            FireAlertObj fireAlertObj = new FireAlertObj();
            fireAlertObj.putData(FireAlertObj.KEY_LINE_JSON, reqMessage);
            new LineEventHandlerChain().doHandler(fireAlertObj);
            return new Message(1, "received");
        } else {
            return new Message(0, "Wrong signature");
        }
    }
}
