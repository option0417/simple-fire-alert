package tw.com.wd.handler;

import com.google.gson.Gson;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.obj.LineEvents;
import tw.com.wd.util.FireAlertLogger;

public class LineEventParserHandler implements IFireAlertHandler {
    @Override
    public void doHandler(FireAlertObj fireAlertObj) {
        String lineJson = fireAlertObj.getData(FireAlertObj.KEY_LINE_JSON);

        LineEvents lineEvents = new Gson().fromJson(lineJson, LineEvents.class);

        FireAlertLogger.info(lineEvents.toString());
    }
}
