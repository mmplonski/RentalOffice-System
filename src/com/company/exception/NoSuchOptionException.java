package com.company.exception;

//jest to wyjatek kontrolowany to znaczy trzeba go obsluzyc
//jest wyrzucany kiedy uzytkownik wprowadzi wartosc niezdefiniowana do Option
public class NoSuchOptionException extends Exception{
    public NoSuchOptionException(String message) {
        super(message);
    }
}
