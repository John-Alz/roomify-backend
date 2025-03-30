package com.roomify.roomifybackend.presentation.advice;

import com.roomify.roomifybackend.services.exception.NoExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NoExistException.class)
    public ResponseEntity<ExceptionResponse> handleRoomNoExistException(NoExistException exception) {
        return  ResponseEntity.badRequest().body(new ExceptionResponse(exception.getMessage(), LocalDate.now()));
    }

}
