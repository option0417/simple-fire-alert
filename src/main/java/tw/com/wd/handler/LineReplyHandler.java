package tw.com.wd.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.message.UnknownMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.objectmapper.ModelObjectMapper;
import tw.com.wd.exception.RuntimeFireAlertException;
import tw.com.wd.io.LineReplyDelivery;
import tw.com.wd.obj.FireAlert;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.obj.MsgCommandType;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LineReplyHandler extends AbstractFireAlertHandler implements IFireAlertHandler {
    private static final TextMessage DEFAULT_MSG_TEXT;

    static {
        ResourceBundle resourceBundle   = ResourceBundle.getBundle("reply_msg", Locale.TAIWAN);
        String defaultMsgText           = resourceBundle.getString("default");
        defaultMsgText                  = new String(defaultMsgText.getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
        DEFAULT_MSG_TEXT                = new TextMessage(defaultMsgText);
    }

    public LineReplyHandler() {
        super();

    }

    @Override
    protected void processing(FireAlertObj fireAlertObj) throws RuntimeFireAlertException {
        List<Event> eventList = fireAlertObj.getData(FireAlertObj.KEY_LINE_EVENT_LIST);

        List<String> replyJsonList = new ArrayList<>();
        for (Event event : eventList) {
            try {
                MessageContent msgContent = fetchContent(event);

                if (isTextMessageContent(msgContent)) {
                    TextMessageContent textMsgContent = (TextMessageContent) msgContent;

                    MsgCommandType msgCommandType = checkCommandType(textMsgContent.getText());

                    switch(msgCommandType) {
                        case New:
                            IFireAlertHandler dataFetcherHandler  = new DataFetcherHandler();
                            IFireAlertHandler dataParserHandler   = new DataParserHandler();

                            ((AbstractFireAlertHandler)dataFetcherHandler)
                                    .addNextHandler(dataParserHandler);

                            dataFetcherHandler.doHandler(fireAlertObj);

                            List<FireAlert> fireAlertList = fireAlertObj.getData(FireAlertObj.KEY_DATA_LIST);
                            StringBuilder fireAlertTextBuilder = new StringBuilder();
                            for (int idx = 0; idx < fireAlertList.size() && idx < 10; idx++) {
                                FireAlert fireAlert = fireAlertList.get(idx);
                                fireAlertTextBuilder.append(fireAlert.toString());
                                fireAlertTextBuilder.append("\n");
                            }

                            Message msg             = new TextMessage(fireAlertTextBuilder.toString());
                            ReplyMessage replyMsg   = new ReplyMessage(((MessageEvent)event).getReplyToken(), msg);
                            String replyJson        = convertToJson(replyMsg);
                            replyJsonList.add(replyJson);
                            break;
                        case None:
                            default:
                                return;
                    }
                }
            } catch (Throwable t) {
                throw new RuntimeFireAlertException(t);
            }
        }
        fireAlertObj.putData(FireAlertObj.KEY_LINE_REPLY_JSONS, replyJsonList);
        new LineReplyDelivery().deliver(fireAlertObj);
    }

    private MsgCommandType checkCommandType(String text) {
        return text == null
                ? MsgCommandType.None
                : "new".equals(text.toLowerCase())
                    ? MsgCommandType.New
                    : MsgCommandType.None;
    }

    private MessageContent fetchContent(Event event) {
        if (event instanceof MessageEvent) {
            MessageEvent msgEvent = (MessageEvent) event;
            return msgEvent.getMessage();
        }
        return new UnknownMessageContent("");
    }

    private boolean isTextMessageContent(MessageContent msgContent) {
        return msgContent instanceof TextMessageContent;
    }

    private String convertToJson(ReplyMessage replyMessage) throws JsonProcessingException {
        return ModelObjectMapper.createNewObjectMapper().writeValueAsString(replyMessage);
    }
}
