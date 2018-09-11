package tw.com.wd.util;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.UserSource;
import com.linecorp.bot.model.objectmapper.ModelObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tw.com.wd.obj.LineEvents;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class LineJsonParserTest {
    private static final String JSON_MESSAGE_EVENT = "{\"replyToken\": \"00000000000000000000000000000000\",\"type\": \"message\",\"timestamp\": 1536602432215,\"source\": {\"type\": \"user\",\"userId\":\"Udeadbeefdeadbeefdeadbeefdeadbeef\"}, \"message\": {\"id\": \"100001\",\"type\": \"text\",\"text\": \"Hello, world\"} }";


    private static String rawReqJson = null;

    @BeforeClass
    public static void beforeClass() {
        InputStream ins = null;
        try {
            ins = ClassLoader.getSystemResourceAsStream("line_req.json");
            byte[] rawJsonBytes = new byte[ins.available()];
            ins.read(rawJsonBytes);

            rawReqJson = new String(rawJsonBytes, Charset.forName("UTF-8"));
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void testFetchEventList() {
        Throwable rtnThrowable  = null;
        LineEvents lineEvents   = null;

        try {
            lineEvents = ModelObjectMapper.createNewObjectMapper().readValue(rawReqJson, LineEvents.class);
        } catch (Throwable t) {
            t.printStackTrace();
            rtnThrowable = t;
        }

        Assert.assertNull(rtnThrowable);
        Assert.assertNotNull(lineEvents);
        Assert.assertTrue(lineEvents.getEvents().size() == 2);
        Assert.assertTrue(lineEvents.getEvents().get(0) instanceof MessageEvent);
        Assert.assertEquals(((MessageEvent)lineEvents.getEvents().get(0)).getReplyToken(), "00000000000000000000000000000000");
        Assert.assertTrue(lineEvents.getEvents().get(0).getSource() instanceof UserSource);
        Assert.assertTrue((((MessageEvent)lineEvents.getEvents().get(0)).getMessage()) instanceof TextMessageContent);
    }


    @Test
    public void testJsonParser() {
        Throwable rtnThrowable  = null;
        Event event             = null;

        try {
            event = ModelObjectMapper.createNewObjectMapper().readValue(JSON_MESSAGE_EVENT, Event.class);
        } catch (Throwable t) {
            rtnThrowable = t;
            t.printStackTrace();
        }

        Assert.assertNull(rtnThrowable);
        Assert.assertNotNull(event);
        Assert.assertTrue(event instanceof MessageEvent);
        Assert.assertEquals(((MessageEvent)event).getReplyToken(), "00000000000000000000000000000000");
        Assert.assertTrue(event.getSource() instanceof UserSource);
        Assert.assertTrue((((MessageEvent)event).getMessage()) instanceof TextMessageContent);
    }
}
