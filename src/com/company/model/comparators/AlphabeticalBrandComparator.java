package com.company.model.comparators;

import com.company.model.Vehicle;

import java.util.Comparator;

//klasa komparatora, pozwala na sortowanie samochod bo nazwie marki
public class AlphabeticalBrandComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle veh1, Vehicle veh2) {
        if (veh1 == null && veh2 == null)
            return 0;
        else if (veh1 == null)
            return 1;
        else if (veh2 == null)
            return -1;
        return veh1.getBrand().compareToIgnoreCase(veh2.getBrand());
    }
}
