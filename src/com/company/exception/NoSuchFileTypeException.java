package com.company.exception;

//jest wyrzucany kiedy uzytkownik wybierze  niezdefiniowany typ danych
public class NoSuchFileTypeException extends RuntimeException{
    public NoSuchFileTypeException(String message) {
        super(message);
    }
}
