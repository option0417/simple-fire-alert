package tw.com.wd.handler;

import tw.com.wd.exception.IllegalSignatureException;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.util.SignatureUtil;

public class LineSignatureHandler extends AbstractFireAlertHandler implements IFireAlertHandler {
    @Override
    protected void processing(FireAlertObj fireAlertObj) {
        String signature    = fireAlertObj.getData(FireAlertObj.KEY_LINE_SIGNATURE);
        String lineJson     = fireAlertObj.getData(FireAlertObj.KEY_LINE_JSON);

        if (!SignatureUtil.isValid(signature, lineJson)) {
            throw new IllegalSignatureException("Signature not match.");
        }
    }
}
