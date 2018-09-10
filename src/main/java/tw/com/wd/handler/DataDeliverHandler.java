package tw.com.wd.handler;

import tw.com.wd.obj.FireAlert;
import tw.com.wd.obj.FireAlertObj;

import java.util.List;

public class DataDeliverHandler extends AbstractFireAlertHandler {
    @Override
    protected void processing(FireAlertObj fireAlertObj) throws Throwable {
        List<FireAlert> fireAlertList = (List<FireAlert>) fireAlertObj.getData(FireAlertObj.KEY_DATA_LIST);
        System.out.printf("DataJson:\n %s", fireAlertList.get(0).toJson());
    }

    private void deliverByHTTP(List<FireAlert> fireAlertList) {




    }
}
