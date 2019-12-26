package name.imh0t3mp.course.geekbrains.config;

import name.imh0t3mp.course.geekbrains.exception.EmailAlreadyUsedException;
import name.imh0t3mp.course.geekbrains.exception.ParameterIllegalException;
import name.imh0t3mp.course.geekbrains.exception.ResourceNotFoundException;
import name.imh0t3mp.course.geekbrains.exception.UsernameAlreadyUsedException;
import name.imh0t3mp.geekbrains.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(HttpServletRequest request, ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO(
                        404,
                        e.getMessage()));
    }

    @ExceptionHandler(ParameterIllegalException.class)
    public ResponseEntity<?> parameterIllegalExceptionHandler(HttpServletRequest request, ParameterIllegalException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(400,
                "Получили неверное начение параметра в URL."));
    }

    @ExceptionHandler(UsernameAlreadyUsedException.class)
    public ResponseEntity<?> userIllegalExceptionHandler(HttpServletRequest request,
                                                         UsernameAlreadyUsedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(400,
                "Пользователь с таким логином уже зарегистрирован."));
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<?> userIllegalExceptionHandler(HttpServletRequest request,
                                                         EmailAlreadyUsedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(400,
                "Пользователь с таким E-Mail уже зарегистрирован."));
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> unauthorizedExceptionHandler(HttpServletRequest request, AccessDeniedException e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorDTO(403,
                        "В доступе отказано."));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> unauthorizedExceptionHandler(HttpServletRequest request, AuthenticationException e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorDTO(403,
                        e.getMessage().isEmpty() ? "В доступе отказано." : e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(
                        500,
                        "Ошибка на сервере."));
    }

}
