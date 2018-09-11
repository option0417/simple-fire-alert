package tw.com.wd;

import org.junit.Assert;
import org.junit.Test;
import tw.com.wd.util.FireAlertLogger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SignatureTest {
    private static final String REQ             = "{\"events\":[{\"type\":\"message\",\"replyToken\":\"da5e7a02215c45b196eee5bbe697bd38\",\"source\":{\"userId\":\"U62e6795d142e8e5810ee71206684280b\",\"type\":\"user\"},\"timestamp\":1536573567131,\"message\":{\"type\":\"text\",\"id\":\"8551292894658\",\"text\":\"Test\"}}]}";
    private static final String REQ_SIGNATURE   = "RSzJ5tYvhKzPNMWLvsoVsOVkVC4zQaXDC1QWzAoYMcc=";
    private static final String SECRET_KEY      = "4e357a2b82d59f117984c14a3fa1324c";


    @Test
    public void testSignature() throws Throwable {
        Mac mac = Mac.getInstance("HmacSHA256");

        SecretKeySpec secret_key = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "HmacSHA256");
        mac.init(secret_key);

        String receivedSignature = new String(Base64.getEncoder().encode(mac.doFinal(REQ.getBytes("UTF-8"))), "UTF-8");

        FireAlertLogger.info("Cal Signature: %s\n", receivedSignature);


        Assert.assertEquals(REQ_SIGNATURE, receivedSignature);
    }
}
