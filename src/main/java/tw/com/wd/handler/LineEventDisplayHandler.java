package tw.com.wd.handler;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.event.source.UserSource;
import tw.com.wd.exception.RuntimeFireAlertException;
import tw.com.wd.obj.FireAlertObj;
import tw.com.wd.util.FireAlertLogger;

import java.util.List;

public class LineEventDisplayHandler extends AbstractFireAlertHandler implements IFireAlertHandler {
    @Override
    protected void processing(FireAlertObj fireAlertObj) throws RuntimeFireAlertException {
        List<Event> eventList = fireAlertObj.getData(FireAlertObj.KEY_LINE_EVENT_LIST);

        for (Event event : eventList) {
            if (event instanceof MessageEvent) {
                MessageEvent msgEvent = (MessageEvent)event;

                showMsgEvent(msgEvent);
            } else {
                FireAlertLogger.error("Unsupport Event: {}", event.getClass().getName());
            }
        }

    }

    private void showMsgEvent(MessageEvent msgEvent) {
        FireAlertLogger.info("ReplyToken: {}", msgEvent.getReplyToken());

        MessageContent msgContent = msgEvent.getMessage();

        /**
         *         @JsonSubTypes.Type(TextMessageContent.class),
         @JsonSubTypes.Type(ImageMessageContent.class),
         @JsonSubTypes.Type(LocationMessageContent.class),
         @JsonSubTypes.Type(AudioMessageContent.class),
         @JsonSubTypes.Type(VideoMessageContent.class),
         @JsonSubTypes.Type(StickerMessageContent.class),
         @JsonSubTypes.Type(FileMessageContent.class),
         */
        FireAlertLogger.info("Message");
        if (msgContent instanceof TextMessageContent) {
            TextMessageContent textMsgContent = (TextMessageContent)msgContent;
            FireAlertLogger.info("\tMessageID: {}", textMsgContent.getId());
            FireAlertLogger.info("\tMessage: {}", textMsgContent.getText());
        }

        FireAlertLogger.info("\tTimestamp: {}", msgEvent.getTimestamp().toString());

        Source src = msgEvent.getSource();
        FireAlertLogger.info("Source");
        if (src instanceof UserSource) {
            UserSource uSrc = (UserSource)src;
            FireAlertLogger.info("\tUserID: {}", uSrc.getUserId());
            FireAlertLogger.info("\tSenderID: {}", uSrc.getSenderId());
        } else if (src instanceof GroupSource) {
            GroupSource gSrc = (GroupSource)src;
            FireAlertLogger.info("\tUserID: {}", gSrc.getUserId());
            FireAlertLogger.info("\tSenderID: {}", gSrc.getSenderId());
            FireAlertLogger.info("\tGroupID: {}", gSrc.getGroupId());
        } else if (src instanceof RoomSource) {
            RoomSource rSrc = (RoomSource)src;
            FireAlertLogger.info("\tUserID: {}", rSrc.getUserId());
            FireAlertLogger.info("\tSenderID: {}", rSrc.getSenderId());
            FireAlertLogger.info("\tRoomID: {}", rSrc.getRoomId());
        }

    }
}
