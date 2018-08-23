package tw.com.wd.service.obj;

import java.util.concurrent.ConcurrentHashMap;

public class FireAlertObj {
    public static final String KEY_DATA_URL = "data_url";
    public static final String KEY_DATA_DOC = "data_doc";
    public static final String KEY_DATA_LIST = "data_list";



    private static final ConcurrentHashMap<String, Object> DATA_MAP = new ConcurrentHashMap<>();

    public void putData(String key, Object dataObj) {
        DATA_MAP.put(key, dataObj);
    }

    public Object getData(String key) {
        return DATA_MAP.get(key);
    }
}
