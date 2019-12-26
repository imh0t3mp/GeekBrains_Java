package name.imh0t3mp.course.geekbrains.exception;

public class UsernameAlreadyUsedException extends ParameterIllegalException{
    public UsernameAlreadyUsedException() {
    }

    public UsernameAlreadyUsedException(String s) {
        super(s);
    }

    public UsernameAlreadyUsedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UsernameAlreadyUsedException(Throwable throwable) {
        super(throwable);
    }
}
