package name.imh0t3mp.course.geekbrains.task_tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book not found")
public class UserNotFoundException extends RuntimeException {
}
