package tw.com.wd.service.api;

import org.springframework.web.bind.annotation.*;
import tw.com.wd.service.obj.Message;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@RestController
public class FireAlertAPI {
    private static final String SECRET_KEY = "4e357a2b82d59f117984c14a3fa1324c";
    @RequestMapping(path = "/hello", method = RequestMethod.POST)
    public Message sayHello(@RequestBody Message message) {
        System.out.println("Say hello to " + message.getContent());
        return new Message(1, "Hello, stranger");
    }

    @RequestMapping(path = "/message", method = RequestMethod.POST)
    public Message sendMessage(@RequestHeader(name = "x-line-signature") String signature, @RequestBody String reqMessage) {

        if (isValid(signature, reqMessage)) {
            System.out.printf("Req: %s\n", reqMessage);
            return new Message(1, "received");
        } else {
            return new Message(0, "Wrong signature");
        }
    }

    private boolean isValid(String signature, String reqBody) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");

            SecretKeySpec secret_key = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "HmacSHA256");
            mac.init(secret_key);

            String receivedSignature = new String(Base64.getEncoder().encode(mac.doFinal()), "UTF-8");

            System.out.printf("Req Signature: %s\n", signature);
            System.out.printf("Cal Signature: %s\n", receivedSignature);

            return signature.equals(receivedSignature);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return false;
    }
}
