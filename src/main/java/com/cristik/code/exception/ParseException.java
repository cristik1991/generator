package com.cristik.code.exception;


/**
 * @author cristik
 */
public class ParseException extends Exception {

    private String message;

    public ParseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
