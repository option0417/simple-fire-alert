package tw.com.wd.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tw.com.wd.handler.LineEventHandlerChain;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.obj.Message;
import tw.com.wd.util.FireAlertLogger;

@RestController
public class FireAlertAPI {
    @RequestMapping(path = "/hello", method = RequestMethod.POST)
    public Message sayHello(@RequestBody Message message) {
        FireAlertLogger.info("Say hello to " + message.getContent());
        return new Message(1, "Hello, stranger");
    }

    @RequestMapping(path = "/message", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Message sendMessage(@RequestHeader(name = "x-line-signature") String signature, @RequestBody String reqMessage) {

        FireAlertLogger.info("Req: {}\n", reqMessage);


        FireAlertObj fireAlertObj = new FireAlertObj();
        fireAlertObj.putData(FireAlertObj.KEY_LINE_SIGNATURE, signature);
        fireAlertObj.putData(FireAlertObj.KEY_LINE_JSON, reqMessage);

        new LineEventHandlerChain().doHandler(fireAlertObj);

        if (fireAlertObj.isSuccess()) {
            return new Message(1, "received");
        } else {
            return new Message(0, "fail");
        }
    }
}
