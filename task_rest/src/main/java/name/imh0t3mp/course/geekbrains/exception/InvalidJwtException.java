package name.imh0t3mp.course.geekbrains.exception;

public class InvalidJwtException extends RuntimeException {

    private static final long serialVersionUID = 8197086462208138875L;

    public InvalidJwtException() {
        super();
    }
    public InvalidJwtException(String s) {
        super(s);
    }
    public InvalidJwtException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public InvalidJwtException(Throwable throwable) {
        super(throwable);
    }
}