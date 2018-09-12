package tw.com.wd.handler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import tw.com.wd.exception.RuntimeFireAlertException;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.util.FireAlertLogger;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DataFetcherHandler extends AbstractFireAlertHandler {
    private URL fireAlertURL;


    public DataFetcherHandler() {
        super();
        try {
            this.fireAlertURL = new URL("https://epaper.fire.ntpc.gov.tw/liveview/default.aspx");
        } catch (MalformedURLException e) {
            throw new RuntimeFireAlertException(e);
        }
    }

    @Override
    protected void processing(FireAlertObj fireAlertObj) throws RuntimeFireAlertException {
        try {
            FireAlertLogger.info("URL: {}", this.fireAlertURL.toString());
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("fire_alert.html").getFile());
            Document doc = Jsoup.parse(this.fireAlertURL, 30000);
            //Document doc = Jsoup.parse(file, "UTF-8");

            fireAlertObj.putData(FireAlertObj.KEY_DATA_DOC, doc);
        } catch (IOException e) {
            throw new RuntimeFireAlertException(e);
        }
    }
}
