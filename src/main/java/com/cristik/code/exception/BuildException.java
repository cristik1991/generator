package com.cristik.code.exception;

/**
 * @author cristik
 */
public class BuildException extends RuntimeException {

    private String message;

    public BuildException(String message){
        this.message = message;
    }

}
