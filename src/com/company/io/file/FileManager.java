package com.company.io.file;

import com.company.model.RentalOffice;

//Interfejs ten denifuje taki kontrakt:
//Kazda klasa implementujaca ten interfejs MUSI posiadac metody do
//słuzace do importu oraz eksportu obiektu typu RentalOffice(baza danaych)
public interface FileManager {
    RentalOffice importData();
    void exportData(RentalOffice rentalOffice);
}
