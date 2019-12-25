package name.imh0t3mp.course.geekbrains.services.error;

public class UsernameAlreadyUsedException extends RuntimeException {

    public UsernameAlreadyUsedException() {
        super("Польззователь с таким именем уже есть в базе!");
    }

}
