package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Parking;
import ru.job4j.ood.lsp.parking.model.SimpleCar;
import ru.job4j.ood.lsp.parking.model.Truck;
import ru.job4j.ood.lsp.parking.parkmasters.ParkMaster;
import ru.job4j.ood.lsp.parking.parkmasters.SimpleParkMaster;
import ru.job4j.ood.lsp.parking.parkmasters.TruckParkMaster;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ParkingControlTest {
    ParkingControl parkingControl;

    @BeforeEach
    private void setUp() {
        List<ParkMaster> parkMasters = new ArrayList<>();
        ParkMaster simpleParkMaster = new SimpleParkMaster();
        ParkMaster truckParkMaster = new TruckParkMaster();
        parkMasters.add(simpleParkMaster);
        parkMasters.add(truckParkMaster);
        this.parkingControl = new ParkingControl(parkMasters);
    }

    @Test
    public void whenParkingSimpleCar() {
        boolean[] simplePlaces = {false, false, false};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        Car car = new SimpleCar();
        boolean[] expected = {true, false, false};
        parkingControl.manageParking(car, parking);
        assertThat(parking.getSimplePlaces()).containsExactly(expected);
    }

    @Test
    public void whenParkingTruck() {
        boolean[] simplePlaces = {false, false, false};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        Car car = new Truck(2);
        boolean[] expected = {true, false, false};
        parkingControl.manageParking(car, parking);
        assertThat(parking.getTruckPlaces()).containsExactly(expected);
    }

    @Test
    public void whenParkingTruckOnSimplePlaces() {
        boolean[] simplePlaces = {false, false, false};
        boolean[] truckPlaces = {true, true, true};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        Car car = new Truck(2);
        boolean[] expected = {true, true, false};
        parkingControl.manageParking(car, parking);
        assertThat(parking.getSimplePlaces()).containsExactly(expected);
    }

    @Test
    public void whenTruckSizeMoreThenEmptySimplePlaces() {
        boolean[] simplePlaces = {true, false, false};
        boolean[] truckPlaces = {true, true, true};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        Car car = new Truck(3);
        assertThatThrownBy(() -> parkingControl.manageParking(car, parking)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenSimplePlacesIsBusyThenException() {
        boolean[] simplePlaces = {true, true, true};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        Car car = new SimpleCar();
        assertThatThrownBy(() -> parkingControl.manageParking(car, parking)).isInstanceOf(RuntimeException.class);
    }
}