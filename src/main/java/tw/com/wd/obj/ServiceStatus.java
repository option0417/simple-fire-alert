package tw.com.wd.obj;

public class ServiceStatus {
    private boolean isSuccess;
    private int code;
    private String message;


    public ServiceStatus() {
        super();
    }

    public ServiceStatus(boolean isSuccess, int code, String message) {
        super();
        this.isSuccess  = isSuccess;
        this.code       = code;
        this.message    = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public ServiceStatus setSuccess(boolean success) {
        isSuccess = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ServiceStatus setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServiceStatus setMessage(String message) {
        this.message = message;
        return this;
    }
}
