package name.imh0t3mp.course.geekbrains.exception;

public class EmailAlreadyUsedException extends ParameterIllegalException {
    public EmailAlreadyUsedException() {
    }

    public EmailAlreadyUsedException(String s) {
        super(s);
    }

    public EmailAlreadyUsedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EmailAlreadyUsedException(Throwable throwable) {
        super(throwable);
    }
}