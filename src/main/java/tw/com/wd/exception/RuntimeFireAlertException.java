package tw.com.wd.exception;

public class RuntimeFireAlertException extends RuntimeException {
    public RuntimeFireAlertException() {
        super();
    }

    public RuntimeFireAlertException(String msg) {
        super(msg);
    }

    public RuntimeFireAlertException(Throwable t) {
        super(t);
    }
}
