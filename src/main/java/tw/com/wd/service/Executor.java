package tw.com.wd.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tw.com.wd.service.handler.FireAlertHandlerChain;
import tw.com.wd.service.obj.FireAlertObj;

import java.net.URL;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Executor {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://epaper.fire.ntpc.gov.tw/liveview/default.aspx");

        FireAlertObj fireAlertObj = new FireAlertObj();
        fireAlertObj.putData(FireAlertObj.KEY_DATA_URL, url);

        new FireAlertHandlerChain().doHandler(fireAlertObj);

        SpringApplication.run(Executor.class, args);
    }
}
