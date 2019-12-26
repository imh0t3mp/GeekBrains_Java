package name.imh0t3mp.course.geekbrains.exception;

public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(resourceName, fieldName, fieldValue);
    }
}