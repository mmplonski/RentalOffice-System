package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//klasa reprezentujaca uzytkownika wypozyczalni
public class RentalUser extends User {
    private List<Vehicle> vehicleHistory = new ArrayList<>();
    private List<Vehicle> borrowedVehicles = new ArrayList<>();

    public List<Vehicle> getVehicleHistory() {
        return vehicleHistory;
    }


    public List<Vehicle> getBorrowedVehicles() {
        return borrowedVehicles;
    }

    public RentalUser(String firstName, String lastName, String pesel) {
        super(firstName, lastName, pesel);
    }

    private void addVehicleHistory(Vehicle veh) {
        vehicleHistory.add(veh);
    }

    public void borrowVehicle(Vehicle veh) {
        borrowedVehicles.add(veh);
    }

    public boolean returnVehicle(Vehicle veh) {
        boolean result = false;
        if (borrowedVehicles.remove(veh)) {
            result = true;
            addVehicleHistory(veh);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RentalUser that = (RentalUser) o;
        return Objects.equals(vehicleHistory, that.vehicleHistory) && Objects.equals(borrowedVehicles, that.borrowedVehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vehicleHistory, borrowedVehicles);
    }


    @Override
    public String toCsv() {
        return null;
    }
}
