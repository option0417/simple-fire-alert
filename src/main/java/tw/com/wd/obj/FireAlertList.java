package tw.com.wd.obj;

import com.google.gson.Gson;

import java.util.List;

public class FireAlertList {
    private static final Gson GSON_INSTANCE = new Gson();
    private List<FireAlert> fireAlertList;

    public FireAlertList(List<FireAlert> fireAlertList) {
        super();
        this.fireAlertList = fireAlertList;
    }

    public List<FireAlert> getFireAlertList() {
        return fireAlertList;
    }

    public String toJson() {
        return GSON_INSTANCE.toJson(this);
    }
}
