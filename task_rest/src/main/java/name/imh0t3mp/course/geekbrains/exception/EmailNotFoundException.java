package name.imh0t3mp.course.geekbrains.exception;

public class EmailNotFoundException extends ResourceNotFoundException{

    public EmailNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(resourceName, fieldName, fieldValue);
    }
}