package com.company.model;

import java.util.Objects;
//klasa reprezentujaca motocykl
public class Motorcycle extends Vehicle {
    public static final String TYPE = "MOTOCYKL";

    private String bodyType;
    private boolean driveOnRoad;

    public Motorcycle(String brand, String model, double engineCapacity, int price, boolean available, String bodyType, boolean driveOnRoad, String vin) {
        super(brand, model, engineCapacity, price, available, vin);
        this.bodyType = bodyType;
        this.driveOnRoad = driveOnRoad;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public boolean isDriveOnRoad() {
        return driveOnRoad;
    }

    public void setDriveOnRoad(boolean driveOnRoad) {
        this.driveOnRoad = driveOnRoad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Motorcycle that = (Motorcycle) o;
        return driveOnRoad == that.driveOnRoad && Objects.equals(bodyType, that.bodyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bodyType, driveOnRoad);
    }

    @Override
    public String toString() {
        return  bodyType + " " + getBrand() + " " + getModel() + " " + getEngineCapacity() + " " +
                driveOnRoad + " " + getPrice() + " " + getVin() + " " + isAvailable();
    }

    @Override
    public String toCsv() {
        return TYPE + ";" +
                getBodyType() + ";" +
                getBrand() + ";" +
                getModel() + ";" +
                getEngineCapacity() + ";" +
                isDriveOnRoad() + ";" +
                getPrice() + ";" +
                getVin() + ";" +
                isAvailable() + "";
    }
}
