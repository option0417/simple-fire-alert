package tw.com.wd.obj;

public class Message {
    private int code;
    private String content;


    public Message() {
        super();
    }

    public Message(int code, String content) {
        super();
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }
}
