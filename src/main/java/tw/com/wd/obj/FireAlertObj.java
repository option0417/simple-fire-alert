package tw.com.wd.obj;

import java.util.HashMap;
import java.util.Map;

public class FireAlertObj {
    public static final String KEY_DATA_URL     = "data_url";
    public static final String KEY_DATA_DOC     = "data_doc";
    public static final String KEY_DATA_LIST    = "data_list";

    // Line related property
    public static final String KEY_LINE_SIGNATURE   = "line_signature";
    public static final String KEY_LINE_JSON        = "line_json";
    public static final String KEY_LINE_REPLY_JSONS  = "line_reply_jsons";
    public static final String KEY_LINE_PUSH_JSONS   = "line_push_jsons";
    public static final String KEY_LINE_EVENT_LIST  = "line_event_list";

    private Map<String, Object> dataMap;
    private ServiceStatus serviceStatus;


    public FireAlertObj() {
        super();
        this.dataMap        = new HashMap<>();
        this.serviceStatus  = new ServiceStatus(false, 0, "");
    }

    public FireAlertObj putData(String key, Object dataObj) {
        this.dataMap.put(key, dataObj);
        return this;
    }

    public <T> T getData(String key) {
        return (T) this.dataMap.get(key);
    }

    public FireAlertObj setSuccess(boolean isSuccess) {
        this.serviceStatus.setSuccess(isSuccess);
        return this;
    }

    public boolean isSuccess() {
        return serviceStatus.isSuccess();
    }
}
