package br.com.listtta.backend.exceptions;

import br.com.listtta.backend.exceptions.files.FileToBigException;
import br.com.listtta.backend.exceptions.files.MimeTypeNotAllowedException;
import br.com.listtta.backend.util.validation.ControllersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalFilesExceptionHandler {
    private final ControllersResponse response;

    @ExceptionHandler(FileToBigException.class)
    public ResponseEntity<Map<String, Object>> handleFileToBigException (FileToBigException exception) {
        return new ResponseEntity<>(response.controllersResponse(null, exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MimeTypeNotAllowedException.class)
    public ResponseEntity<Map<String, Object>> handleMimeTypeNotAllowedException (MimeTypeNotAllowedException exception) {
        return new ResponseEntity<>(response.controllersResponse(null, exception), HttpStatus.BAD_REQUEST);
    }
}
