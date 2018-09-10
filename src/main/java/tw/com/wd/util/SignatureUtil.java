package tw.com.wd.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;

public class SignatureUtil {
    private static final String SECRET_KEY = "4e357a2b82d59f117984c14a3fa1324c";

    public static boolean isValid(String reqSignature, String reqBody) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "HmacSHA256");
            byte[] reqBodyBytes = reqBody.getBytes(Charset.forName("UTF-8"));

            mac.init(secret_key);
            byte[] base64ReqBytes = Base64.getEncoder().encode(mac.doFinal(reqBodyBytes));

            String receivedSignature = new String(base64ReqBytes, "UTF-8");

            System.out.printf("Req Signature: %s\n", reqSignature);
            System.out.printf("Cal Signature: %s\n", receivedSignature);
            return reqSignature.equals(receivedSignature);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return false;
    }
}
