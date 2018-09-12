package tw.com.wd.flow;

import tw.com.wd.obj.FireAlertObj;

public interface IFireAlertServiceFlow {
    /**
     * Trigger FireAlert service flow
     * @param fireAlertObj
     */
    public void trigger(FireAlertObj fireAlertObj);
}
