package tw.com.wd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tw.com.wd.flow.FireAlertDeliveryServiceFlow;
import tw.com.wd.obj.FireAlertObj;

import java.net.URL;


@SpringBootApplication
public class Executor {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Executor.class, args);
    }

    private static final void doFireAlertHandlerChain() throws Exception {
        URL url = new URL("http://epaper.fire.ntpc.gov.tw/liveview/default.aspx");
        FireAlertObj fireAlertObj = new FireAlertObj();
        fireAlertObj.putData(FireAlertObj.KEY_DATA_URL, url);
        new FireAlertDeliveryServiceFlow().trigger(fireAlertObj);
    }
}
