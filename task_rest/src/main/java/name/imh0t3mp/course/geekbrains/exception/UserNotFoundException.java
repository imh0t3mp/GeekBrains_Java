package name.imh0t3mp.course.geekbrains.exception;

import name.imh0t3mp.course.geekbrains.config.ResourceName;

public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(String fieldName, Object fieldValue) {
        super(ResourceName.USER, fieldName, fieldValue);
    }
}