package name.imh0t3mp.course.geekbrains.services.error;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("Неправильный пароль пользователя");
    }

}
