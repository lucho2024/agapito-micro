package com.microservicios.rest.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@JsonIgnoreProperties({"stackTrace","cause","localizedMessage","message","suppressed","httpStatus"})
public abstract class GeneralException extends RuntimeException {

    private static final long serialVersionUID = -1911031301733703684L;

    private HttpStatus httpStatus;
    private String code;
    private String type;
    private transient Object details;
    private String moreInfo;
    private  String location;
    private String uuid;
    private String timestamp;


    GeneralException(String message, HttpStatus httpStatus){
        this((String)message,httpStatus,message,(String)null);
    }

    GeneralException(String message, HttpStatus httpStatus, Object details){
        this((String)message,httpStatus,details,(String)null );
    }

    GeneralException(String message, HttpStatus httpStatus, Object details, String location){
        super(message);
        this.httpStatus=httpStatus;
        this.code=String.valueOf(this.httpStatus.value());
        this.type=ExceptionUtils.getErrorTypeFromHttpStatus(httpStatus).toString();
        this.details=details;
        this.location=location;
        this.timestamp= LocalDateTime.now().toString();
    }

    GeneralException(Exception exception, HttpStatus httpStatus){
        this((Exception)exception,httpStatus,httpStatus.getReasonPhrase(),(String)null);
    }

    GeneralException(Exception exception, HttpStatus httpStatus, Object details){
        this((Exception) exception,httpStatus,details,(String)null);
    }

    public GeneralException(Exception exception, HttpStatus httpStatus, Object details, String location){
        super(exception);
        this.httpStatus=httpStatus;
        this.code=String.valueOf(this.httpStatus.value());
        this.type=ExceptionUtils.getErrorTypeFromHttpStatus(httpStatus).toString();
        this.details=details;
        this.location=location;
        this.timestamp= LocalDateTime.now().toString();

    }

    public GeneralException(Exception exception, HttpStatus httpStatus, Object details, String location,
                            String type, String moreInfo){
        super(exception);
        this.httpStatus=httpStatus;
        this.code=String.valueOf(this.httpStatus.value());
        this.type=type;
        this.details=details;
        this.location=location;
        this.moreInfo=moreInfo;
        this.timestamp= LocalDateTime.now().toString();

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String,Object> generateError(){
        Map<String,Object> errorAttributes = new HashMap<>();

        errorAttributes.put("details",this.getDetails());
        errorAttributes.put("code",this.getCode());
        errorAttributes.put("moreInfo",this.getMoreInfo());
        errorAttributes.put("location",this.getLocation());
        ErrorType errorType = ExceptionUtils.getErrorTypeFromHttpStatus(this.getHttpStatus());
        errorAttributes.put("type",errorType);
        return errorAttributes;
    }






}
