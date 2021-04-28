package com.company.app;

import com.company.exception.DataExportException;
import com.company.exception.DataImportException;
import com.company.exception.InvalidDataException;
import com.company.exception.UserExistisException;
import com.company.io.ConsolePrinter;
import com.company.io.DataReader;
import com.company.io.EmailSender;
import com.company.io.file.FileManager;
import com.company.io.file.FileManagerBuilder;
import com.company.model.*;
import org.apache.commons.mail.EmailException;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

//ta klasa jest odpowiedzialna za tworzenie obiektow  i sterowaniem programu
public class RentalControl {

    private ConsolePrinter printer = new ConsolePrinter(); //wyswietlania
    private DataReader dataReader = new DataReader(printer); //wczytywanie danych
    private FileManager fileManager; //do tej zmiennej mozna przypisac tylko obiekt ktory posiada mozliwosc importu/eksportu danych


    private RentalOffice rentalOffice; //zmienna bazy dannych, pozwala na operowanie na danych o aktualnej liczbie pojazdow i uzytkownikow

    RentalControl() {
        //dzieki temu mam mozliwosc operacji na danych za pomoca zmiennej fileManager
        fileManager = new FileManagerBuilder(printer, dataReader).bulid();
        try {
            rentalOffice = fileManager.importData();
            printer.printLine("Zaimportowano dane  z pliku");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("Utworzono nową baze danych.");
            rentalOffice = new RentalOffice();
        }
    }

    //Głowna pętla programu pozwalajcą na wybór opcji i interakcje
    public void controlLoop() {
        Option option;

        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_CAR:
                    addCar();
                    break;
                case ADD_MOTORCYCLE:
                    addMoto();
                    break;
                case PRINT_CARS:
                    printCars();
                    break;
                case PRINT_MOTORCYCLES:
                    printMoto();
                    break;
                case DELETE_CAR:
                    deleteCar();
                    break;
                case DELETE_MOTORCYCLE:
                    deleteMoto();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case PRINT_USERS:
                    printUsers();
                    break;
                case FIND_USER_ByPESEL:
                    findByPesel();
                    break;
                case SENND_EMAIL:
                    sendEmail();
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadz ponownie: ");
            }
        } while (option != Option.EXIT);
    }

    //metoda tworzaca wiadomosc email
    private void sendEmail() {
        printer.printLine("Podaj adres: ");
        String adres = dataReader.getString();
        printer.printLine("Treść: ");
        String content = dataReader.getString();
        try {
            EmailSender.sendEmail(content,adres);
        } catch (EmailException e) {
            System.out.println("Nie udało sie wyslac emaila");
        }
    }

    //znajdywanie uzytkownikow za pomoca pesela
    private void findByPesel() {
        printer.printLine("Podaj Pesel: ");
        String pesel = dataReader.getString();
        String notFoundMess = "Brak uzytkownika o takim tytule";

        //szukanie uzytkownika po peselu, zamiana na reprezentacje String, jesli uzytkownik istenije
        //to znaczy nie jest pustym Optionalem to zwraca go wyswietla w przeciwnym wypadku
        //wyswietlony jest napis "Brak uzytkownika o takim tytule"
        rentalOffice.fintUserByPesel(pesel)
                .map(RentalUser::toString)
                .ifPresentOrElse(System.out::println,() -> System.out.println(notFoundMess));

    }


    // tutaj mozna tez przekazac obiekt comparatora
    //poniewaz getSortedVehicles() przyjmuje komparator
    private void printCars() {
        printer.printCars(rentalOffice.getSortedVehicles(Comparator.comparing(Vehicle::getBrand, String.CASE_INSENSITIVE_ORDER)));

    }

    private void printMoto() {
        printer.printMots(rentalOffice.getSortedVehicles(Comparator.comparing(Vehicle::getBrand, String.CASE_INSENSITIVE_ORDER)));

    }


    //tutaj zostal przekazany obiekt klasy anonimowej Comparator poniewaz
    //nie utworzyłem comparatora

    private void printUsers() {
        printer.printUsers(rentalOffice.getSortedUsers(Comparator.comparing(User::getLastName, String.CASE_INSENSITIVE_ORDER)));

    }


    //proste wyswietlenie opcji

    private void printOptions() {
        System.out.println("Wybierz opcje: ");
        for (Option value : Option.values()) {
            System.out.println(value);
        }
    }

    private void addCar() {
        try {
            Car car = dataReader.readAndCreateCar();
            rentalOffice.addVehicle(car);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało sie utworzyc samocohdu, wprowadzono niepoprawne dane");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Osiągnieto limit pojemności.");
        }
    }


//    private Vehicle[] getSortedVehicles() {
//        Vehicle[] vehicles = rentalOffice.getVehicles();
//        Arrays.sort(vehicles,new AlphabeticalBrandComparator());
//        return vehicles;
//    }

    private void addMoto() {
        try {
            Motorcycle motorcycle = dataReader.readAndCreateMoto();
            rentalOffice.addVehicle(motorcycle);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało sie utworzyc motocyklu, wprowadzono niepoprawne dane");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Osiągnieto limit pojemności.");
        }
    }



    private void addUser() {
        RentalUser rentalUser = dataReader.readAndCreateUser();
        try {
            rentalOffice.addUser(rentalUser);
        } catch (UserExistisException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void deleteMoto() {
        try {
            Motorcycle moto = dataReader.readAndCreateMoto();
            if (rentalOffice.deleteVehicle(moto)) {
                printer.printLine("Usunieto motocykl z bazy danych");
            } else {
                System.out.println("Brak wskaznaego motocykla w bazie danych.");
            }
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało sie utworzyc motocyklu, wprowadzono nieprawidłowe dane");
        }
    }

    private void deleteCar() {
        try {
            Car car = dataReader.readAndCreateCar();
            if (rentalOffice.deleteVehicle(car)) {
                printer.printLine("Usunieto samochod z bazy danych");
            } else {
                System.out.println("Brak wskaznaego samochodu w bazie danych.");
            }
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało sie utworzyc samochodu, wprowadzono nieprawidłowe dane");
        }
    }

    private Option getOption() {
        boolean optionOK = false;
        Option option = null;
        while (!optionOK) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOK = true;
            } catch (InputMismatchException e) {
                printer.printLine("Wprowadzona wartosc nie jest liczbą, podaj ponownie");
            } catch (NoSuchElementException e) {
                printer.printLine(e.getMessage() + ", podaj ponownie liczbe");
            }
        }
        return option;
    }

    private void exit() {
        try {
            fileManager.exportData(rentalOffice);
            printer.printLine("Export danych zakonczony powodzeniem");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        dataReader.close();
        printer.printLine("Dowiedzenia !");
    }




    private enum Option {
        EXIT(0, "Wylacz program."),
        ADD_CAR(1, "Dodanie samochodu do bazy wypożyczalni."),
        ADD_MOTORCYCLE(2, "Dodanie motocykla do bazy wypożyczalni"),
        PRINT_CARS(3, "Wyświetlenie dostępnych samochodów."),
        PRINT_MOTORCYCLES(4, "WYświetlenie dostępnych motocykli."),
        DELETE_CAR(5, "Usuń samochod"),
        DELETE_MOTORCYCLE(6, "Usuń motocykl"),
        ADD_USER(7,"Dodaj klienta"),
        PRINT_USERS(8,"Wyswietl klientow"),
        FIND_USER_ByPESEL(9,"Wyszukaj klienta po peselu: "),
        SENND_EMAIL(10,"Wyslij emaila");


        private int value;
        private String description;


        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        //jest to metoda która pozwala przekształcic liczbe na odpowiedni obiekt option
        //przyjmuje liczbe z tablicy zwroconej przez values() i zwraca obiekt o danym indx
        //NoSuchElementException precyzuje co poszlo nie tak
        static Option createFromInt(int option) throws NoSuchElementException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) { // uzytkownik odwolal sie do wartosci spoza Option wiec wyrzucic
                throw new NoSuchElementException("Nie ma takiej opcji o id" + option);
            }
        }
    }

}

