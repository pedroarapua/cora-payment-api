package com.organization.payment.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  //500
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, 
      HttpHeaders headers,
      HttpStatus status, 
      WebRequest request) {

    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", new Date());
    body.put("status", status.value());

    // Get all errors
    List<String> errors = ex.getBindingResult()
        .getAllErrors()
        .stream()
        .map(x -> x.getDefaultMessage())
        .collect(Collectors.toList());
    
    body.put("errors", errors);

    return new ResponseEntity<>(body, headers, status);

  }

  //400
  @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex, 
      WebRequest request) {
    
    Optional<Class<?>> type = Optional.ofNullable(ex.getRequiredType());
    
    String name = "";
    if (type.isPresent()) {
      name = type.get().getName();
    }
    
    String error = 
        ex.getName() + " should be of type " + name;
 
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", new Date());
    body.put("status", HttpStatus.BAD_REQUEST.value());
    
    // Get all errors
    List<String> errors = new ArrayList<String>();
    errors.add(error);

    body.put("errors", errors);
    return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }
  
  //400
  @Override
  public ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, 
      HttpHeaders headers, 
      HttpStatus status, 
      WebRequest request) {
    
    String error = ex.getMessage();
 
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", new Date());
    body.put("status", status.value());
    
    // Get all errors
    List<String> errors = new ArrayList<String>();
    errors.add(error);

    body.put("errors", errors);
    return new ResponseEntity<>(body, headers, status);
  }
  
  //404
  @ExceptionHandler(value = { EntityNotFoundException.class })
  protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {      
    
    String error = ex.getMessage() + " not found";
 
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", new Date());
    body.put("status", HttpStatus.NOT_FOUND.value());
    
    // Get all errors
    List<String> errors = new ArrayList<String>();
    errors.add(error);

    body.put("errors", errors);
    return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

}
