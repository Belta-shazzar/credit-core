package org.credicore.user.exception;

import org.credicore.user.exception.custom.BadRequestException;
import org.credicore.user.exception.custom.ConflictException;
import org.credicore.user.exception.custom.ForbiddenException;
import org.credicore.user.exception.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorMessage> notFoundException(NotFoundException ex) {
    ErrorMessage message = new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND,
            ex.getMessage()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorMessage> conflictException(ConflictException ex) {
    ErrorMessage message = new ErrorMessage(
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT,
            ex.getMessage()
    );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorMessage> badRequestException(BadRequestException ex) {
    ErrorMessage message = new ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST,
            ex.getMessage()
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ErrorMessage> forbiddenException(ForbiddenException ex) {
    ErrorMessage message = new ErrorMessage(
            HttpStatus.FORBIDDEN.value(),
            HttpStatus.FORBIDDEN,
            ex.getMessage()
    );

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
  }
}
