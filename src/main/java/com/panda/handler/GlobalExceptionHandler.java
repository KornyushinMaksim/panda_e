package com.panda.handler;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionMessageObject> handleConstraintViolationException(ValidationException exception) {
        ExceptionMessageObject message = ExceptionMessageObject.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("невозможно добавить пользователя или логин")
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
