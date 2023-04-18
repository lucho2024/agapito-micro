package com.microservicios.rest.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class NotFoundException extends RuntimeException{

    private String message;
    private HttpStatus  httpStatus;
    private String location;


}
