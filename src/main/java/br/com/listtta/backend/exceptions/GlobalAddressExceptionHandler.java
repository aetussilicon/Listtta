package br.com.listtta.backend.exceptions;

import br.com.listtta.backend.exceptions.address.CannotSaveAddressException;
import br.com.listtta.backend.exceptions.address.NoUserAddressException;
import br.com.listtta.backend.util.validation.ControllersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalAddressExceptionHandler {
    private final ControllersResponse response;

    @ExceptionHandler(CannotSaveAddressException.class)
    public ResponseEntity<Map<String, Object>> handleCannotSaveAddressException(CannotSaveAddressException exception) {
        return new ResponseEntity<>(response.controllersResponse(null, exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoUserAddressException.class)
    public ResponseEntity<Map<String, Object>> handleNoUserAddressException(NoUserAddressException exception) {
        return new ResponseEntity<>(response.controllersResponse(null, exception), HttpStatus.BAD_REQUEST);
    }
}
