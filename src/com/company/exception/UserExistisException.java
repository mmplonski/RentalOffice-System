package com.company.exception;

public class UserExistisException extends RuntimeException{
    public UserExistisException(String message) {
        super(message);
    }
}
