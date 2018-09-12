package tw.com.wd.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.objectmapper.ModelObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import tw.com.wd.obj.FireAlert;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.util.FireAlertLogger;

import java.util.ArrayList;
import java.util.List;

public class HTTPDataDelivery implements IDataDelivery {
    /** https://developers.line.me/en/reference/messaging-api/#send-push-message **/
    private static final String POST_TARGET = "https://api.line.me/v2/bot/message/push";

    private static final String ACCEDD_TOKEN = "tdEAWDexjqahWsbJ2zeam02d7HS658svQWKsPIQQ6hiLR5DnLoxjWEJ2uwa4qgLpAeNmhf+JSjfp05EVDwOHKwY6iGRP+E6qpx3xE01op1l36Z1gvXEEVPqCXW91ATSq6fTmNi1al00BRPvarRREzgdB04t89/1O/w1cDnyilFU=";

    @Override
    public void deliver(FireAlertObj fireAlertObj) {
        List<FireAlert> fireAlertList = fireAlertObj.getData(FireAlertObj.KEY_DATA_LIST);

        CloseableHttpResponse response = null;
        String fireAlertText = fireAlertList.get(0).toString();

        List<Message> textMsgList = new ArrayList<>();
        textMsgList.add(new TextMessage(fireAlertText));

        PushMessage pushMessage = new PushMessage("U62e6795d142e8e5810ee71206684280b", textMsgList);

        String reqJson = null;
        try {
            reqJson = ModelObjectMapper.createNewObjectMapper().writeValueAsString(pushMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            HttpPost post = new HttpPost(POST_TARGET);

            post.addHeader("key", "value");
            post.addHeader("Authorization", "Bearer {" + ACCEDD_TOKEN + "}");

            post.setEntity(new StringEntity(reqJson, ContentType.APPLICATION_JSON));

            CloseableHttpClient httpclient = HttpClients.createDefault();

            response = httpclient.execute(post);

            FireAlertLogger.info(response.getStatusLine().toString());
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
