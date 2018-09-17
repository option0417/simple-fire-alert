package tw.com.wd.handler;

import com.linecorp.bot.model.message.TextMessage;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

public class LineReplyHandlerTest {
    private static final String DEFAULT_REPLY_MSG = "請輸入\n1.板橋區\n2.新莊區\n3.中和區\n4.永和區\n5.土城區\n6.樹林區\n7.三峽區\n8.鶯歌區\n9.三重區\n10.蘆洲區\n11.五股區\n12.泰山區\n13.林口區\n14.八里區\n15.淡水區\n16.三芝區\n17.石門區\n18.金山區\n19.萬里區\n20.汐止區\n21.瑞芳區\n22.貢寮區\n23.平溪區\n24.雙溪區\n25.新店區\n26.深坑區\n27.石碇區\n28.坪林區";

    @Test
    public void testTextMessage() {
        Throwable rtnThrowable  = null;
        String text             = null;
        TextMessage textMsg     = null;

        try {
            text = new String(ResourceBundle.getBundle("reply_msg", Locale.TAIWAN).getString("default").getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
            textMsg = new TextMessage(text);
        } catch (Throwable t) {
            rtnThrowable = t;
        }

        Assert.assertNull(rtnThrowable);
        Assert.assertNotNull(text);
        Assert.assertNotNull(textMsg);
        Assert.assertEquals(DEFAULT_REPLY_MSG, text);
        Assert.assertEquals(DEFAULT_REPLY_MSG, textMsg.getText());
    }
}
