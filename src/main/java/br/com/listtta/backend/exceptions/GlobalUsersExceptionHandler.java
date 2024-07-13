package br.com.listtta.backend.exceptions;

import br.com.listtta.backend.exceptions.users.*;
import br.com.listtta.backend.util.validation.ControllersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalUsersExceptionHandler {

    private final ControllersResponse response;

    @ExceptionHandler(UserNotAllowedException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotAllowedException(UserNotAllowedException exception, WebRequest request) {
        return new ResponseEntity<>(response.controllersResponse(null, exception), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CannotRegisterUserException.class)
    public ResponseEntity<Map<String, Object>> handleCannotRegisterUserException(CannotRegisterUserException exception, WebRequest request) {
        return new ResponseEntity<>(response.controllersResponse(null, exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotUpdateUsersFieldsException.class)
    public ResponseEntity<Map<String, Object>> handleCannotUpdateUsersFieldException(CannotUpdateUsersFieldsException exception, WebRequest request) {
        return new ResponseEntity<>(response.controllersResponse(null, exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyInDatabaseException.class)
    public ResponseEntity<Map<String, Object>> handleUserAlredyInDatabaseException(UserAlreadyInDatabaseException exception, WebRequest request) {
        return new ResponseEntity<>(response.controllersResponse(null, exception), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(response.controllersResponse(null, exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(response.controllersResponse(null,exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
