package tw.com.wd.util;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.objectmapper.ModelObjectMapper;
import tw.com.wd.obj.LineEvents;

import java.io.IOException;
import java.util.List;

public class LineJsonParser {
    public static final List<Event> fetchEventList(String reqLineJson) throws IOException {
        LineEvents lineEvents = ModelObjectMapper.createNewObjectMapper().readValue(reqLineJson, LineEvents.class);
        return lineEvents.getEvents();
    }
}
