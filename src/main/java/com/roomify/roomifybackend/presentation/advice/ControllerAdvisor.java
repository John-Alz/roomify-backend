package com.roomify.roomifybackend.presentation.advice;

import com.roomify.roomifybackend.services.exception.CheckInInvalidException;
import com.roomify.roomifybackend.services.exception.CheckOutInvalidException;
import com.roomify.roomifybackend.services.exception.NoAvailabilityException;
import com.roomify.roomifybackend.services.exception.NoExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NoExistException.class)
    public ResponseEntity<ExceptionResponse> handleRoomNoExistException(NoExistException exception) {
        return  ResponseEntity.badRequest().body(new ExceptionResponse(exception.getMessage(), LocalDate.now()));
    }

    @ExceptionHandler(CheckInInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleCheckInInvalidException(CheckInInvalidException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse("Fecha de CheckIn debe ser mayor al hoy.", LocalDate.now()));
    }

    @ExceptionHandler(CheckOutInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleCheckOutInvalidException(CheckOutInvalidException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse("Fecha de CheckOut debe ser mayor a la fecha de checkIn.", LocalDate.now()));
    }

    @ExceptionHandler(NoAvailabilityException.class)
    public ResponseEntity<ExceptionResponse> handleAvailabilityException(NoAvailabilityException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse("No hay disponibilidad para el tipo de habitación en las fechas seleccionadas.", LocalDate.now()));
    }

}
