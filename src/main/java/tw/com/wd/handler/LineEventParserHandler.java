package tw.com.wd.handler;

import com.linecorp.bot.model.event.Event;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.util.LineJsonParser;

import java.io.IOException;
import java.util.List;

public class LineEventParserHandler extends AbstractFireAlertHandler implements IFireAlertHandler {
    @Override
    protected void processing(FireAlertObj fireAlertObj) {
        try {
            String lineJson = fireAlertObj.getData(FireAlertObj.KEY_LINE_JSON);
            List<Event> eventList = LineJsonParser.fetchEventList(lineJson);

            fireAlertObj.putData(FireAlertObj.KEY_LINE_EVENT_LIST, eventList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
