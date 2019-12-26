package name.imh0t3mp.course.geekbrains.exception;

import org.springframework.security.access.AccessDeniedException;

public class InvalidPasswordException extends AccessDeniedException {

    public InvalidPasswordException() {
        super("Неправильный логин/пароль");
    }

    public InvalidPasswordException(String msg) {
        super(msg);
    }

    public InvalidPasswordException(String msg, Throwable t) {
        super(msg, t);
    }
}