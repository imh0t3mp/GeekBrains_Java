package name.imh0t3mp.geekbrains.dto;

/**
 * Для вывода сообщений об ошибках
 */
public class ErrorDTO {

    private int errorCode;
    private String message;

    public ErrorDTO() {
    }

    public ErrorDTO(int errorCode, String message) {
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

}
