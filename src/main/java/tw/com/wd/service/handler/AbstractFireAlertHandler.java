package tw.com.wd.service.handler;

import tw.com.wd.service.obj.FireAlertObj;

public abstract class AbstractFireAlertHandler implements IFireAlertHandler {
    private AbstractFireAlertHandler nextHandler;


    public void doHandler(FireAlertObj fireAlertObj) {
        try {
            processing(fireAlertObj);

            if (null != nextHandler) {
                nextHandler.doHandler(fireAlertObj);
            }
        } catch (Throwable t) {

        }
    }

    public AbstractFireAlertHandler addNextHandler(IFireAlertHandler nextHandler) {
        this.nextHandler = (AbstractFireAlertHandler) nextHandler;
        return this.nextHandler;
    }

    protected abstract void processing(FireAlertObj fireAlertObj) throws Throwable;
}
