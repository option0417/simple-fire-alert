package tw.com.wd.handler;

import tw.com.wd.exception.RuntimeFireAlertException;
import tw.com.wd.obj.FireAlert;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.util.FireAlertLogger;

import java.util.List;

public class DataDeliverHandler extends AbstractFireAlertHandler {
    @Override
    protected void processing(FireAlertObj fireAlertObj) throws RuntimeFireAlertException {
        List<FireAlert> fireAlertList = (List<FireAlert>) fireAlertObj.getData(FireAlertObj.KEY_DATA_LIST);
        FireAlertLogger.info("DataJson:\n %s", fireAlertList.get(0).toJson());
    }

    private void deliverByHTTP(List<FireAlert> fireAlertList) {




    }
}
