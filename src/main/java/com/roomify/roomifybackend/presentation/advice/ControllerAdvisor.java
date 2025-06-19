package com.roomify.roomifybackend.presentation.advice;

import com.roomify.roomifybackend.services.exception.*;
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

    @ExceptionHandler(InvalidBookingStatus.class)
    public ResponseEntity<ExceptionResponse> handleInvalidBookingStatus(InvalidBookingStatus e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse("Solo se pueden asignar habitaciones a reservas confirmadas.", LocalDate.now()));
    }

    @ExceptionHandler(InvalidSizeRoomsIds.class)
    public ResponseEntity<ExceptionResponse> handleInvalidSizeRoomsIds(InvalidSizeRoomsIds e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse("Debes asignar el numero exacto de habitaciones.", LocalDate.now()));
    }

    @ExceptionHandler(RoomWithBookingActiveException.class)
    public ResponseEntity<ExceptionResponse> handleRoomWithBookingActiveExceptions(RoomWithBookingActiveException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse("La habitacion tiene reservas asociadas.", LocalDate.now()));
    }

    //

}
