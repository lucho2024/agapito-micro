package com.microservicios.rest.controller.advice;

import com.microservicios.rest.error.ErrorDetails;
import com.microservicios.rest.error.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDetails>  handleNotFoundException(NotFoundException ex,
                                                                       WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false), ex.getLocation());

        return new ResponseEntity<>(errorDetails,ex.getHttpStatus());
    }

}
