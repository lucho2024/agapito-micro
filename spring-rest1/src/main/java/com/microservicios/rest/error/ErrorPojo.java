package com.microservicios.rest.error;

public class ErrorPojo {

    private String message;

    private String location;

    public ErrorPojo(String message, String location) {
        this.message = message;
        this.location = location;
    }

    public ErrorPojo() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
