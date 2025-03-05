package ru.job4j.ood.lsp.parking.parkmasters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Parking;

import static org.assertj.core.api.Assertions.*;

@Disabled
class SimpleParkMasterTest {
    @Test
    public void whenCarSizeIsNot1ThenNotParked() {
        boolean[] simplePlaces = {false, false, false};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        ParkMaster parkMaster = new SimpleParkMaster();
        boolean[] expected = {false, false, false};
        parkMaster.parkCar(parking, 2);
        assertThat(parking.getSimplePlaces()).containsExactly(expected);
        assertThat(parking.getTruckPlaces()).containsExactly(expected);
    }

    @Test
    public void whenParkToFreeParking() {
        boolean[] simplePlaces = {false, false, false};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        ParkMaster parkMaster = new SimpleParkMaster();
        boolean[] expectedSimplePlaces = {true, false, false};
        boolean[] expectedTruckPlaces = {false, false, false};
        parkMaster.parkCar(parking, 1);
        assertThat(parking.getSimplePlaces()).containsExactly(expectedSimplePlaces);
        assertThat(parking.getTruckPlaces()).containsExactly(expectedTruckPlaces);
    }

    @Test
    public void whenParkToLastPlace() {
        boolean[] simplePlaces = {true, true, false};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        ParkMaster parkMaster = new SimpleParkMaster();
        boolean[] expectedSimplePlaces = {true, true, true};
        boolean[] expectedTruckPlaces = {false, false, false};
        parkMaster.parkCar(parking, 1);
        assertThat(parking.getSimplePlaces()).containsExactly(expectedSimplePlaces);
        assertThat(parking.getTruckPlaces()).containsExactly(expectedTruckPlaces);
    }

    @Test
    public void whenParkingIsBusyThenException() {
        boolean[] simplePlaces = {true, true, true};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        ParkMaster parkMaster = new SimpleParkMaster();
        assertThatThrownBy(() -> parkMaster.parkCar(parking, 1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenParkingIsNullThenException() {
        Parking parking = null;
        ParkMaster parkMaster = new SimpleParkMaster();
        assertThatThrownBy(() -> parkMaster.parkCar(parking, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenInvalidSizeThenException() {
        boolean[] simplePlaces = {false, false, false};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        ParkMaster parkMaster = new SimpleParkMaster();
        assertThatThrownBy(() -> parkMaster.parkCar(parking, 0)).isInstanceOf(IllegalArgumentException.class);
    }
}