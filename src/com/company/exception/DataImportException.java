package com.company.exception;

//ten wyjatek jest stworzony zeby uspojnic informacje o błedzie odczytu
public class DataImportException extends RuntimeException{
    public DataImportException(String message) {
        super(message);
    }
}
