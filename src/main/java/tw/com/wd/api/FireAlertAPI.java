package tw.com.wd.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tw.com.wd.flow.FireAlertDeliveryServiceFlow;
import tw.com.wd.flow.IFireAlertServiceFlow;
import tw.com.wd.flow.LineReplyServiceFlow;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.obj.Message;
import tw.com.wd.util.FireAlertLogger;

@RestController
public class FireAlertAPI {
    private IFireAlertServiceFlow fireAlertDeliveryServiceFlow;
    private IFireAlertServiceFlow lineReplyServiceFlow;


    public FireAlertAPI() {
        super();
        this.fireAlertDeliveryServiceFlow   = new FireAlertDeliveryServiceFlow();
        this.lineReplyServiceFlow           = new LineReplyServiceFlow();
    }

    @RequestMapping(path = "/message", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Message sendMessage(@RequestHeader(name = "x-line-signature") String signature, @RequestBody String reqMessage) {
        FireAlertLogger.info("Req: {}\n", reqMessage);

        FireAlertObj fireAlertObj = new FireAlertObj();
        fireAlertObj.putData(FireAlertObj.KEY_LINE_SIGNATURE, signature);
        fireAlertObj.putData(FireAlertObj.KEY_LINE_JSON, reqMessage);

        this.lineReplyServiceFlow.trigger(fireAlertObj);
        if (fireAlertObj.isSuccess()) {
            return new Message(1, "received");
        } else {
            return new Message(0, "fail");
        }
    }

    @RequestMapping(path = "/message", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void getMessage() {
        this.fireAlertDeliveryServiceFlow.trigger(new FireAlertObj());
    }
}
