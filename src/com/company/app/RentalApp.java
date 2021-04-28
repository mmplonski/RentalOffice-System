package com.company.app;

import org.apache.commons.mail.EmailException;

//to jest glowna klasa programu jej zadania ogranicza sie do
//zainicjowania odpowiednich obiektow oraz wystarowania odpowiedniej metody
//sterujacej w klasie RentalControl
public class RentalApp {
    public static void main(String[] args) throws EmailException {
       RentalControl rentalControl = new RentalControl();
       rentalControl.controlLoop();



    }
}
