package com.company.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Vehicle implements Serializable, Comparable<Vehicle>, CsvConvertible  {
    private String brand;
    private String model;

    private double engineCapacity;
    private int price;

    private boolean available;
    private String vin;

    //porownywanie poprzez marke
    @Override
    public int compareTo(Vehicle o) {
        return brand.compareToIgnoreCase(o.brand);
    }

    //zasieg pakietowy poniewaz metod poniewaz nei chce żeby ktoś tworzył lub "grzebał"
    //przy obiektach Vehicle
    Vehicle(String brand, String model, double engineCapacity, int price, boolean available, String vin) {
        this.brand = brand;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.price = price;
        this.available = available;
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    void setBrand(String brand) {
        this.brand = brand;
    }

    String getModel() {
        return model;
    }

    void setModel(String model) {
        this.model = model;
    }

    double getEngineCapacity() {
        return engineCapacity;
    }

    void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public boolean isAvailable() {
        return available;
    }

    int getPrice() {
        return price;
    }

    void setPrice(int price) {
        this.price = price;
    }

    void setAvailable(boolean available) {
        this.available = available;
    }

    public String getVin() {
        return vin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.engineCapacity, engineCapacity) == 0 && price == vehicle.price && available == vehicle.available && Objects.equals(brand, vehicle.brand) && Objects.equals(model, vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, engineCapacity, price, available);
    }

    @Override
    public String toString() {
        return getBrand() + " " + getModel() + " " + getEngineCapacity() + " " +
                getPrice() + " " + isAvailable() + vin;
    }


}
