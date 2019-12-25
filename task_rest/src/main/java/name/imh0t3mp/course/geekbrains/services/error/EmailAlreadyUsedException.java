package name.imh0t3mp.course.geekbrains.services.error;

public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException() {
        super("Пользователь с таким EMail уже есть в базе!");
    }

}
