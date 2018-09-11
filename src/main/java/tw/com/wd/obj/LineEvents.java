package tw.com.wd.obj;

import com.linecorp.bot.model.event.Event;

import java.util.List;

public class LineEvents {
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> linEventList) {
        this.events = linEventList;
    }
}
