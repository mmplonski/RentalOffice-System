package com.company.model;


import com.company.exception.UserExistisException;
import com.company.exception.VehicleExistsException;

import java.io.Serializable;
import java.util.*;

//zadaniem tej klasy jest przechowywanie informacji o pojazdach do wypozyczenia i uzytkownikach
public class RentalOffice implements Serializable {
    private Map<String, Vehicle> vehicles = new HashMap<>();
    private Map<String, RentalUser> users = new HashMap<>();

    public Map<String, Vehicle> getVehicles() {
        return vehicles;
    }

    public Map<String, RentalUser> getUser() {
        return users;
    }

    public Optional<RentalUser> fintUserByPesel(String pesel){
        return Optional.ofNullable(users.get(pesel));
    }


    //Ta metoda przyjmuje jakiś komparator który określa w jakis sposób ma byc posortowana kolekcja
    //Tworzy liste i zamienia przekazana kolekcje w liste
    //sortuje i zwraca liste
    //mozna tak zrobic bo lista dziedziczy po kolekcji
    public Collection<Vehicle> getSortedVehicles(Comparator<Vehicle> comparator){
        ArrayList<Vehicle> list = new ArrayList<>(this.vehicles.values());
        list.sort(comparator);
        return list;
    }

    //przyjmuje obiekt komparatora i zwraca liste uzytkownikow posrtowanych wedlug tego komparatora
    public Collection<RentalUser> getSortedUsers(Comparator<RentalUser> comparator){
        ArrayList<RentalUser> list = new ArrayList<>(this.users.values());
        list.sort(comparator);
        return list;
    }

    public void addUser(RentalUser rentalUser){
        if (users.containsKey(rentalUser.getPesel()))
            throw new UserExistisException("Uzytkownik o takim numerze pesel juz istnieje w bazie " + rentalUser.getPesel());
        users.put(rentalUser.getPesel(),rentalUser);
    }

    public void addVehicle(Vehicle vehicle){
        if (vehicles.containsKey(vehicle.getVin()))
            throw new VehicleExistsException("Pojazd o takim numerze VIN juz istnieje w bazie");
        vehicles.put(vehicle.getVin(),vehicle);
    }

    public boolean deleteVehicle(Vehicle vehicle){
        if (vehicles.containsValue(vehicle)){
            vehicles.remove(vehicle.getVin());
            return true;
        } else {
            return false;
        }

    }


    public void addCar(Car car) {
        addVehicle(car);
    }

    public void addMoto(Motorcycle moto) {
        addVehicle(moto);
    }


}