package tw.com.wd.handler;

import tw.com.wd.exception.RuntimeFireAlertException;
import tw.com.wd.io.LineReplyDelivery;
import tw.com.wd.obj.FireAlertObj;

public class LineReplyDeliverHandler extends AbstractFireAlertHandler implements IFireAlertHandler {
    private static final LineReplyDelivery REPLY_DELIVERY = new LineReplyDelivery();

    @Override
    protected void processing(FireAlertObj fireAlertObj) throws RuntimeFireAlertException {
        REPLY_DELIVERY.deliver(fireAlertObj);
    }
}
