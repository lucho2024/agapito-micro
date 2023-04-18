package com.microservicios.rest.error;

import org.springframework.http.HttpStatus;

public class GenericException extends  GeneralException {


    private static final long serialVersionUID = 6200285980326008988L;

    public GenericException(String message, Object details){
        super(message, HttpStatus.UNPROCESSABLE_ENTITY,details);
    }

    public GenericException(String message, Object details, String location){
        super(message,HttpStatus.UNPROCESSABLE_ENTITY,details,location);
    }

    public GenericException(Exception exception, Object details){
        super(exception,HttpStatus.UNPROCESSABLE_ENTITY,details);
    }

    public GenericException(Exception exception, Object details, String location){
        super(exception,HttpStatus.UNPROCESSABLE_ENTITY,details,location);
    }

    public GenericException(Exception exception, HttpStatus httpStatus,
                            Object details, String location,
                            String type, String moreInfo){
        super(exception,httpStatus,details,location,type,moreInfo);
    }

}
