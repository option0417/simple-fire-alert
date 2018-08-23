package tw.com.wd.service.handler;

import tw.com.wd.service.obj.FireAlert;
import tw.com.wd.service.obj.FireAlertObj;

import java.util.List;

public class DataDeliverHandler extends AbstractFireAlertHandler {
    @Override
    protected void processing(FireAlertObj fireAlertObj) throws Throwable {
        List<FireAlert> fireAlertList = (List<FireAlert>) fireAlertObj.getData(FireAlertObj.KEY_DATA_LIST);
        System.out.printf("DataJson:\n %s", fireAlertList.get(0).toJson());
    }
}
