package com.company.io.file;

import com.company.exception.NoSuchFileTypeException;
import com.company.io.ConsolePrinter;
import com.company.io.DataReader;

import javax.xml.crypto.Data;

public class FileManagerBuilder {
    //wyswietlanie komunikatow
    private ConsolePrinter printer;
    //odczyt danych
    private DataReader dataReader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader dataReader) {
        this.printer = printer;
        this.dataReader = dataReader;
    }

    //ta metoda tworzy obiekty ktore umożliwiają zapis i odczyt danych
    //w wybranym formacie
    public FileManager bulid() {
        printer.printLine("Wybierz format danych: ");
        FileType fileType = getFileType();
        switch (fileType) {
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("Nieobsługiwany typ danych");
        }
    }

    //ta metoda przekształca wczytany od uzytkownika napis na wartosc typu FileType
    private FileType getFileType() {
        boolean typeOK = false;
        FileType result = null;
        do {
            printTypes();
            String type = dataReader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOK = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("nieobsługiwany typ danych, wybierz ponownie");
            }
        } while (!typeOK);
        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.name());
        }
    }


}
