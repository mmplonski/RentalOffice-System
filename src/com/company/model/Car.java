package com.company.model;

import java.util.Objects;
//klasa reprezentujaca samochod
public class Car extends Vehicle {
    public static final String TYPE = "SAMOCHOD";
    private boolean xDrive;
    private String fuelType;


    public String getFuelType() {
        return fuelType;
    }

    public Car(String brand, String model, double engineCapacity, boolean available, boolean xDrive, String fuelType, int price, String vin) {
        super(brand, model, engineCapacity, price, available, vin);
        this.xDrive = xDrive;
        this.fuelType = fuelType;
    }


    public boolean isxDrive() {
        return xDrive;
    }

    public void setxDrive(boolean xDrive) {
        this.xDrive = xDrive;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return xDrive == car.xDrive && Objects.equals(fuelType, car.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), xDrive, fuelType);
    }

    @Override
    public String toString() {
        return "Marka:" + getBrand().toUpperCase() + " Model:" + getModel().toUpperCase() + " Pojemnośc silnika:" + getEngineCapacity() + " Rodzaj Paliwa:" +
                fuelType.toUpperCase() + " Naped 4x4:" + xDrive + " Cena za dobe:" + getPrice() + " Numer Vin:" + getVin().toUpperCase() + " Dostępnośc: " + isAvailable();
    }


    @Override
    public String toCsv() {
        return TYPE + ";" +
                getBrand() + ";" +
                getModel() + ";" +
                getEngineCapacity() + ";" +
                getFuelType() + ";" +
                isxDrive() + ";" +
                getPrice() + ";" +
                getVin() + ";" +
                isAvailable() + "";
    }
}
