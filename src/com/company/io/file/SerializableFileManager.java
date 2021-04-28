package com.company.io.file;

import com.company.exception.DataExportException;
import com.company.exception.DataImportException;
import com.company.model.RentalOffice;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "RentalOffice.txt";


    //zapis danych do pliku
    //przyjmuje obiekt rentalOffice i go bedzie zapisywac do pliku
    @Override
    public void exportData(RentalOffice rentalOffice) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(rentalOffice);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku: " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Blad zapisu do pliku " + FILE_NAME);
        }
    }

    //odczyt danych z pliku
    @Override
    public RentalOffice importData() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (RentalOffice) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku: " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Bład odczytu pliku " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych  pliku " + FILE_NAME);
        }
    }
}
