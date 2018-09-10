package tw.com.wd.obj;

import com.google.gson.annotations.SerializedName;
import com.linecorp.bot.model.event.Event;
import tw.com.wd.util.FireAlertLogger;

import java.util.List;

public class LineEvents {
    @SerializedName("events")
    private List<Event> lineEventList;


    public List<Event> getLineEventList() {
        return lineEventList;
    }

    public void setLineEventList(List<Event> lineEventList) {
        this.lineEventList = lineEventList;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("LineEvents: ");

        if (lineEventList.size() > 0) {
            strBuilder.append("[");
            for (Event event : lineEventList) {
                FireAlertLogger.info(event.getClass().getSimpleName());
                FireAlertLogger.info(", ");
            }
            strBuilder.deleteCharAt(strBuilder.length() - 1);
            strBuilder.append("]");
        }

        return strBuilder.toString();
    }
}
