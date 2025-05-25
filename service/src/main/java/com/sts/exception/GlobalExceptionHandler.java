package com.sts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sts.model.ResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ResponseData<Error>> handleAuthorizationDeniedException(Exception ex, WebRequest request) {
        log.error("Authorization error: ", ex);
        ResponseData<Error> responseData = handleErrorCode(HttpStatus.FORBIDDEN.value(), ex.getMessage());
        return ResponseEntity.ok(responseData);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData<Error>> handleGlobalException(Exception ex, WebRequest request) {
        log.error("Server exception: ", ex);
        ResponseData<Error> responseData = handleErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.ok(responseData);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseData<Error>> handleValidationException(ValidationException ex) {
        log.error("Validation error: ", ex);
        var error = ex.getErrorCode();
        ResponseData<Error> responseData = handleErrorCode(error.getCode(), error.getMessage());
        return ResponseEntity.ok(responseData);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseData<Error>> handleValidationErrors(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ResponseData<Error> responseData = handleErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.join(", ", errors));
        return ResponseEntity.ok(responseData);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseData<Error>> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        log.error("Username not found error: ", ex);
        ResponseData<Error> responseData = handleErrorCode(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.ok(responseData);
    }

    private ResponseData<Error> handleErrorCode(int httpErrorCode, String errorMessage) {
        Error error = Error.builder()
                .code(httpErrorCode)
                .message(errorMessage)
                .build();

        ResponseData<Error> responseData = new ResponseData<>();
        responseData.setError(error);

        return responseData;
    }
}
