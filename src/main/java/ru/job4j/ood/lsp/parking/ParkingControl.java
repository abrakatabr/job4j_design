package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Parking;
import ru.job4j.ood.lsp.parking.parkmasters.ParkMaster;

import java.util.ArrayList;
import java.util.List;

public class ParkingControl {
    private List<ParkMaster> parkMasters = new ArrayList<>();

    public ParkingControl(List<ParkMaster> parkMasters) {
        this.parkMasters = parkMasters;
    }

    public void manageParking(Car car, Parking parking) {
        for (ParkMaster parkMaster : parkMasters) {
            parkMaster.parkCar(parking, car.getSize());
        }
    }
}
