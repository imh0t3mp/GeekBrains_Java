package name.imh0t3mp.geekbrains.dto;

import java.io.Serializable;

public class ErrorDTO implements Serializable {
    private static final long serialVersionUID = 6125190243149476515L;

    private int errorCode;
    private String message;

    public ErrorDTO() {
    }

    public ErrorDTO(int errorCode, String  message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                '}';
    }
}
