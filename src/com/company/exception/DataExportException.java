package com.company.exception;
//ten wyjatek jest stworzony zeby uspojnic informacje o błedzie zapisu
public class DataExportException extends RuntimeException{
    public DataExportException(String message) {
        super(message);
    }
}
