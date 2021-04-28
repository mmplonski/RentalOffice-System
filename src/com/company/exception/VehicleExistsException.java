package com.company.exception;

public class VehicleExistsException extends RuntimeException{
    public VehicleExistsException(String message) {
        super(message);
    }
}
