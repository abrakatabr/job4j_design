package ru.job4j.ood.lsp.parking.parkmasters;

import ru.job4j.ood.lsp.parking.model.Parking;

public class SimpleParkMaster implements ParkMaster {
    @Override
    public void parkCar(Parking parking, int size) {
        validate(parking, size);
        if (size == 1) {
            checkSimplePlaces(parking, size);
        }
    }

    private void checkSimplePlaces(Parking parking, int size) {
        boolean isFree = false;
        boolean[] simplePlaces = parking.getSimplePlaces();
        for (int i = 0; i < simplePlaces.length; i++) {
            if (!simplePlaces[i]) {
                simplePlaces[i] = true;
                isFree = true;
                break;
            }
        }
        if (!isFree) {
            throw new RuntimeException("No parking spaces!!!!!!!!!!");
        }
    }
}
