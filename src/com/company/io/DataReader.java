package com.company.io;

import com.company.model.Car;
import com.company.model.Motorcycle;
import com.company.model.RentalUser;

import java.util.Scanner;

// ta klasa służy do interakcji z uzytkownikiem to znaczy pozwala na wyświetlanie i pobieranie danych
public class DataReader {
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter consolePrinter;


    //obiekt consolePrinter tworze w konstruktorze poniewaaż chce żeby w apliakcji był tylko jeden taki obiekt
    public DataReader(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void close() {
        sc.close();
    }


    public String getString() {
        return sc.nextLine();
    }

    //blok finally czysci strumien niezaleznei od tego co w nim sie znajduje
    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }


    public double getDouble() {
        try {
            return sc.nextDouble();
        } finally {
            sc.nextLine();
        }
    }

    public Car readAndCreateCar() {
        System.out.println("Marka: ");
        String brand = sc.nextLine();
        System.out.println("Model: ");
        String model = sc.nextLine();
        System.out.println("Pojemoność silnika: ");
        double engineCapacity = getDouble();
        System.out.println("Rodzaj paliwa: ");
        String typeOfFuel = sc.nextLine();
        System.out.println("X drive: ");
        boolean xDrive = sc.nextBoolean();
        sc.nextLine();
        System.out.println("Cena za dobe: ");
        int price = getInt();
        System.out.println("Numer Vin");
        String vin = sc.nextLine();
        System.out.println("Dostępność");
        boolean available = sc.nextBoolean();
        sc.nextLine();
        return new Car(brand, model, engineCapacity, available, xDrive, typeOfFuel, price, vin);
    }

    public Motorcycle readAndCreateMoto() {
        System.out.println("Typ: ");
        String bodyType = sc.nextLine();
        System.out.println("Marka: ");
        String brand = sc.nextLine();
        System.out.println("Model: ");
        String model = sc.nextLine();
        System.out.println("Pojemoność silnika: ");
        double engineCapacity = getDouble();
        System.out.println("Homologacja: ");
        boolean driveOnRoad = sc.nextBoolean();
        sc.nextLine();
        System.out.println("Cena za dobe: ");
        int price = getInt();
        System.out.println("Numer Vin");
        String vin = sc.nextLine();
        System.out.println("Dostępność");
        boolean available = sc.nextBoolean();
        sc.nextLine();
        return new Motorcycle(brand, model, engineCapacity, price, available, bodyType, driveOnRoad, vin);
    }


    public RentalUser readAndCreateUser() {
        consolePrinter.printLine("Imię: ");
        String firstName = sc.nextLine();
        consolePrinter.printLine("Nazwisko: ");
        String lastName = sc.nextLine();
        consolePrinter.printLine("Pesel: ");
        String pesel = sc.nextLine();
        return new RentalUser(firstName,lastName,pesel);
    }
}
