package com.company.exception;

//ten wyjatek jest stworzony zeby uspojnic informacje o niewlasciwym formacie danych
public class InvalidDataException  extends RuntimeException{
    public InvalidDataException(String message) {
        super(message);
    }
}
