package tw.com.wd.handler;

import tw.com.wd.exception.RuntimeFireAlertException;
import tw.com.wd.obj.FireAlertObj;

public abstract class AbstractFireAlertHandler implements IFireAlertHandler {
    private AbstractFireAlertHandler nextHandler;


    public void doHandler(FireAlertObj fireAlertObj) {
        try {
            processing(fireAlertObj);

            if (null != nextHandler) {
                nextHandler.doHandler(fireAlertObj);
            }
        } catch (RuntimeFireAlertException rt) {
            rt.printStackTrace();
            throw rt;
        }
    }

    public AbstractFireAlertHandler addNextHandler(IFireAlertHandler nextHandler) {
        this.nextHandler = (AbstractFireAlertHandler) nextHandler;
        return this.nextHandler;
    }

    protected abstract void processing(FireAlertObj fireAlertObj) throws RuntimeFireAlertException;
}
