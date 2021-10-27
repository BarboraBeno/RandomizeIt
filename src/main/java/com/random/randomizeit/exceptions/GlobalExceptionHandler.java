package com.random.randomizeit.exceptions;

import com.random.randomizeit.models.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AnswerNotFoundException.class)
  public ResponseEntity<Object> AnswerNotFound(AnswerNotFoundException e) {
    return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(QuestionIsRequiredException.class)
  public ResponseEntity<Object> QuestionRequired(QuestionIsRequiredException e){
    return new ResponseEntity<>(new ErrorDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
  }

}
