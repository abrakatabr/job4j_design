package ru.job4j.ood.lsp.parking.parkmasters;

import ru.job4j.ood.lsp.parking.ParkingControl;
import ru.job4j.ood.lsp.parking.model.Parking;

public interface ParkMaster {
    void parkCar(Parking parking, int size);

    default void validate(Parking parking, int size) {
        if (parking == null) {
            throw new IllegalArgumentException("Parking is not defined");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Invalid size");
        }
    }
}
