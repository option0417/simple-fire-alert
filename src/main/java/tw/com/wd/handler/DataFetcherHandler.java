package tw.com.wd.handler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import tw.com.wd.exception.RuntimeFireAlertException;
import tw.com.wd.obj.FireAlertObj;

import java.io.File;
import java.io.IOException;

public class DataFetcherHandler extends AbstractFireAlertHandler {
    @Override
    protected void processing(FireAlertObj fireAlertObj) throws RuntimeFireAlertException {
        //URL dataURL     = (URL) fireAlertObj.getData(FireAlertObj.KEY_DATA_URL);

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("fire_alert.html").getFile());
            Document doc = Jsoup.parse(file, "UTF-8");

            fireAlertObj.putData(FireAlertObj.KEY_DATA_DOC, doc);
        } catch (IOException e) {
            throw new RuntimeFireAlertException(e);
        }
    }
}
