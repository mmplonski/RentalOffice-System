package com.company.io.file;


import com.company.exception.DataExportException;
import com.company.exception.DataImportException;
import com.company.exception.InvalidDataException;
import com.company.model.*;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;

//format csv pozwala na zapis danych w formie czytelnej dla czlowieka
//dzieki niemu mozna pracowac na danych w innych programach np  excel
//bedzie to tekst rodzielony srednikami
//kazdy wiersz bedzie reprezentowal pojedyczny obiekt
public class CsvFileManager implements FileManager {
    private static final String VEHICLE_FILE_NAME = "RentalOffice.csv";
    private static final String USERS_FILE_NAME = "Rental_Users.csv";


    //export dziala w takim sposob ze pobiera wszystkie pojazdy
    //dla kazdego pojazdu
    //zapisuje go w pliku i przechodzi do nastepnej linii

    @Override
    public void exportData(RentalOffice rentalOffice) {
        exportVehicles(rentalOffice);
        exportUsers(rentalOffice);
    }

    private void exportUsers(RentalOffice rentalOffice) {
        Collection<RentalUser> rentalUsers = rentalOffice.getUser().values();
        exportToCsv(rentalUsers, USERS_FILE_NAME);
    }

    private void exportVehicles(RentalOffice rentalOffice) {
        Collection<Vehicle> vehicles = rentalOffice.getVehicles().values();
        exportToCsv(vehicles, VEHICLE_FILE_NAME);
    }

    private <T extends CsvConvertible> void exportToCsv(Collection<T> collection, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (T t : collection) {
                bufferedWriter.write(t.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Blad zapisu danych do pliku: " + fileName);
        }
    }

    @Override
    public RentalOffice importData() {
        RentalOffice rentalOffice = new RentalOffice();
        importVehicles(rentalOffice);
        importUsers(rentalOffice);
        return rentalOffice;
    }

    private void importUsers(RentalOffice rentalOffice) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USERS_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createUserFromString)
                    .forEach(rentalOffice::addUser);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + USERS_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + USERS_FILE_NAME);
        }
    }

    private void importVehicles(RentalOffice rentalOffice) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(VEHICLE_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createVehicleFromString)
                    .forEach(rentalOffice::addVehicle);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku: " + VEHICLE_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + VEHICLE_FILE_NAME);
        }
    }

    private Vehicle createVehicleFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if (Car.TYPE.equals(type)) {
            return createCar(split);
        } else if (Motorcycle.TYPE.equals(type)) {
            return createMotorcycle(split);
        }
        throw new InvalidDataException("Nieznany typ pojazdu: " + type);
    }

    private Car createCar(String[] data) {
        String brand = data[1];
        String model = data[2];
        double engineCapacity = Double.parseDouble(data[3]);
        String fuelType = data[4];
        boolean xDrive = Boolean.parseBoolean(data[5]);
        int price = Integer.parseInt(data[6]);
        String vin = data[7];
        boolean available = Boolean.parseBoolean(data[8]);


        return new Car(brand, model, engineCapacity, available, xDrive, fuelType, price, vin);
    }

    private Motorcycle createMotorcycle(String[] data) {
        String bodyType = data[1];
        String brand = data[2];
        String model = data[3];
        double engineCapacity = Double.parseDouble(data[4]);
        boolean available = Boolean.parseBoolean(data[5]);
        int price = Integer.parseInt(data[6]);
        String vin = data[7];
        boolean driveOnRoad = Boolean.parseBoolean(data[8]);

        return new Motorcycle(brand, model, engineCapacity, price, available, bodyType, driveOnRoad, vin);
    }

    private RentalUser createUserFromString(String line) {
        String[] split = line.split(";");
        String firstName = split[0];
        String lastName = split[1];
        String pesel = split[2];
        return new RentalUser(firstName, lastName, pesel);
    }

}
























