package tw.com.wd.handler;

import com.google.gson.Gson;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.obj.LineEvents;
import tw.com.wd.util.FireAlertLogger;

public class LineEventParserHandler extends AbstractFireAlertHandler implements IFireAlertHandler {
    @Override
    protected void processing(FireAlertObj fireAlertObj) {
        String lineJson = fireAlertObj.getData(FireAlertObj.KEY_LINE_JSON);

        LineEvents lineEvents = new Gson().fromJson(lineJson, LineEvents.class);

        FireAlertLogger.info(lineEvents.toString());
    }
}
