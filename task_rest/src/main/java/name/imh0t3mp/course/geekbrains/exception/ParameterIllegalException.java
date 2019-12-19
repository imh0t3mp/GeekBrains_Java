package name.imh0t3mp.course.geekbrains.exception;

public class ParameterIllegalException extends RuntimeException {

    private static final long serialVersionUID = 8197086462208138875L;

    public ParameterIllegalException() {
        super();
    }
    public ParameterIllegalException(String s) {
        super(s);
    }
    public ParameterIllegalException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public ParameterIllegalException(Throwable throwable) {
        super(throwable);
    }
}