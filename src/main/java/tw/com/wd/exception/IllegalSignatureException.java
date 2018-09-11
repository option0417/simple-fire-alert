package tw.com.wd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Signature not match")
public class IllegalSignatureException extends RuntimeFireAlertException {
    public IllegalSignatureException() {
        super();
    }

    public IllegalSignatureException(String msg) {
        super(msg);
    }

    public IllegalSignatureException(Throwable t) {
        super(t);
    }
}
