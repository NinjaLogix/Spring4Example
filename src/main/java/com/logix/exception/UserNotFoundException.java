package com.logix.exception;

/**
 * Used to rollback transactions if any user is not found during a CRUD action.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){}

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(Throwable cause){
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
