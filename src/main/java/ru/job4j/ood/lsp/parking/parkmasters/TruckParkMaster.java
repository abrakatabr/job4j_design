package ru.job4j.ood.lsp.parking.parkmasters;

import ru.job4j.ood.lsp.parking.model.Parking;

public class TruckParkMaster implements ParkMaster {
    @Override
    public void parkCar(Parking parking, int size) {
        validate(parking, size);
        if (size != 1) {
            checkTruckPlaces(parking, size);
        }
    }

    private void checkTruckPlaces(Parking parking, int size) {
        boolean isFree = false;
        boolean[] simplePlaces = parking.getSimplePlaces();
        boolean[] truckPlaces = parking.getTruckPlaces();
        for (int i = 0; i < truckPlaces.length; i++) {
            if (!truckPlaces[i]) {
                truckPlaces[i] = true;
                isFree = true;
                break;
            }
        }
        if (!isFree) {
            int count = 0;
            for (int i = 0; i < simplePlaces.length; i++) {
                if (!simplePlaces[i]) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == size) {
                    for (int j = 0; j < size; j++) {
                        simplePlaces[i - j] = true;
                    }
                    isFree = true;
                }
            }
        }
        if (!isFree) {
            throw new RuntimeException("No parking spaces!");
        }
    }
}
