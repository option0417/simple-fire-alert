package tw.com.wd.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.objectmapper.ModelObjectMapper;
import tw.com.wd.exception.RuntimeFireAlertException;
import tw.com.wd.io.LineReplyDelivery;
import tw.com.wd.obj.FireAlertObj;

import java.util.ArrayList;
import java.util.List;

public class LineReplyHandler extends AbstractFireAlertHandler implements IFireAlertHandler {
    @Override
    protected void processing(FireAlertObj fireAlertObj) throws RuntimeFireAlertException {
        List<Event> eventList = fireAlertObj.getData(FireAlertObj.KEY_LINE_EVENT_LIST);

        List<String> replyJsonList = new ArrayList<>();
        for (Event event : eventList) {
            try {
                if (event instanceof MessageEvent) {
                    MessageEvent msgEvent = (MessageEvent) event;

                    List<Message> msgList = new ArrayList<>();
                    if (msgEvent.getMessage() instanceof TextMessageContent) {
                        TextMessageContent textMsgContent = (TextMessageContent) msgEvent.getMessage();

                        msgList.add(new TextMessage("Got your message: [" + textMsgContent.getText() + "]"));
                    }

                    ReplyMessage replyMsg = new ReplyMessage(msgEvent.getReplyToken(), msgList);
                    String replyJson = convertToJson(replyMsg);
                    replyJsonList.add(replyJson);
                }
            } catch (Throwable t) {
                throw new RuntimeFireAlertException(t);
            }
        }
        fireAlertObj.putData(FireAlertObj.KEY_LINE_REPLY_JSONS, replyJsonList);
        new LineReplyDelivery().deliver(fireAlertObj);
    }

    private String convertToJson(ReplyMessage replyMessage) throws JsonProcessingException {
        return ModelObjectMapper.createNewObjectMapper().writeValueAsString(replyMessage);
    }
}
