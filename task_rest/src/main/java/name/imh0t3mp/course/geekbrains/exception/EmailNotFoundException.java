package name.imh0t3mp.course.geekbrains.exception;

import name.imh0t3mp.course.geekbrains.config.ResourceName;

public class EmailNotFoundException extends ResourceNotFoundException {

    public EmailNotFoundException(String fieldName, Object fieldValue) {
        super(ResourceName.USER, fieldName, fieldValue);
    }
}