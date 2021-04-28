package com.company.io;

import com.company.model.*;

import java.util.Collection;

//ta funkcja służy do tego żeby skoncentrować wyświeltanie na ekranie
// to znaczy jak bym korzystał z println to musiałbym zmiany wprowadzać w kazdym printlanie
public class ConsolePrinter {

    public void printCars(Collection<Vehicle> vehicles){
        long count = vehicles.stream()
                .filter(v -> v instanceof Vehicle)
                .map(Vehicle::toString)
                .peek(this::printLine)
                .count();
        if (count == 0)
            printLine("Brak samochodow  w bazie.");
    }

    public void printMots(Collection<Vehicle> vehicles){
       long count = vehicles.stream()
               .filter(m -> m instanceof Motorcycle)
               .map(Vehicle::toString)
               .peek(this::printLine)
               .count();
        if (count == 0)
            printLine("Brak motocykli  w bazie.");
    }

    public void printUsers(Collection<RentalUser> users){
        users.stream()
                .map(User::toString)
                .forEach(this::printLine);
    }

    public void printLine(String s) {
        System.out.println(s);
    }
}
