package tw.com.wd.io;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import tw.com.wd.obj.FireAlert;
import tw.com.wd.obj.FireAlertList;
import tw.com.wd.obj.FireAlertObj;

import java.util.List;

public class HTTPDataDelivery implements IDataDelivery {
    /** https://developers.line.me/en/reference/messaging-api/#send-push-message **/
    private static final String POST_TARGET = "https://api.line.me/v2/bot/message/push";

    private static final String ACCEDD_TOKEN = "tdEAWDexjqahWsbJ2zeam02d7HS658svQWKsPIQQ6hiLR5DnLoxjWEJ2uwa4qgLpAeNmhf+JSjfp05EVDwOHKwY6iGRP+E6qpx3xE01op1l36Z1gvXEEVPqCXW91ATSq6fTmNi1al00BRPvarRREzgdB04t89/1O/w1cDnyilFU=";

    @Override
    public void deliver(FireAlertObj fireAlertObj) {
        List<FireAlert> fireAlertList = (List<FireAlert>) fireAlertObj.getData(FireAlertObj.KEY_DATA_LIST);

        CloseableHttpResponse response = null;
        String reqJson = new FireAlertList(fireAlertList).toJson();

        try {
            HttpPost post = new HttpPost(POST_TARGET);

            post.addHeader("key", "value");
            post.addHeader("Authorization", "Bearer {" + ACCEDD_TOKEN + "}");

            post.setEntity(new StringEntity(reqJson, ContentType.APPLICATION_JSON));

            CloseableHttpClient httpclient = HttpClients.createDefault();

            response = httpclient.execute(post);

            System.out.println(response.getStatusLine());
        } catch (Throwable t) {
            return;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }
}
