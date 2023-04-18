package com.microservicios.rest.error;

import org.springframework.http.HttpStatus;

public class ExceptionUtils {

    public ExceptionUtils() {
    }

    public static ErrorType getErrorTypeFromHttpStatus(HttpStatus httpStatus){
        ErrorType errorType;

        switch (httpStatus){
            case BAD_REQUEST:
            case UNAUTHORIZED:
            case FORBIDDEN:
            case NOT_FOUND:
            case METHOD_NOT_ALLOWED:
            case  UNPROCESSABLE_ENTITY:
                errorType = ErrorType.ERROR;
                break;
            case INTERNAL_SERVER_ERROR:
                errorType = ErrorType.FATAL;
                break;
            default:
                errorType = ErrorType.ERROR;
        }
        return errorType;
    }
}
