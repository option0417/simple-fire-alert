package tw.com.wd.io;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.util.FireAlertLogger;

import java.util.List;

public class LineReplyDelivery implements IDataDelivery {
    private static final String POST_TARGET = "https://api.line.me/v2/bot/message/reply";
    private static final String ACCEDD_TOKEN = "tdEAWDexjqahWsbJ2zeam02d7HS658svQWKsPIQQ6hiLR5DnLoxjWEJ2uwa4qgLpAeNmhf+JSjfp05EVDwOHKwY6iGRP+E6qpx3xE01op1l36Z1gvXEEVPqCXW91ATSq6fTmNi1al00BRPvarRREzgdB04t89/1O/w1cDnyilFU=";


    @Override
    public void deliver(FireAlertObj fireAlertObj) {
        List<String> replyJsonList = fireAlertObj.getData(FireAlertObj.KEY_LINE_REPLY_JSONS);

        for (String replyJson : replyJsonList) {
            CloseableHttpResponse response = null;
            try {
                HttpPost post = new HttpPost(POST_TARGET);
                post.addHeader("key", "value");
                post.addHeader("Authorization", "Bearer {" + ACCEDD_TOKEN + "}");
                post.setEntity(new StringEntity(replyJson, ContentType.APPLICATION_JSON));

                CloseableHttpClient httpclient = HttpClients.createDefault();
                response = httpclient.execute(post);
                FireAlertLogger.info(response.getStatusLine().toString());
            } catch (Throwable t) {
                t.printStackTrace();
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
}
