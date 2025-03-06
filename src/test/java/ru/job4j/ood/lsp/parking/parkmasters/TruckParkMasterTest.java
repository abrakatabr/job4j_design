package ru.job4j.ood.lsp.parking.parkmasters;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Parking;

import static org.assertj.core.api.Assertions.*;

class TruckParkMasterTest {
    @Test
    public void whenParkToFreeParking() {
        boolean[] simplePlaces = {false, false, false};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        ParkMaster parkMaster = new TruckParkMaster();
        boolean[] expectedSimplePlaces = {false, false, false};
        boolean[] expectedTruckPlaces = {true, false, false};
        parkMaster.parkCar(parking, 2);
        assertThat(parking.getSimplePlaces()).containsExactly(expectedSimplePlaces);
        assertThat(parking.getTruckPlaces()).containsExactly(expectedTruckPlaces);
    }

    @Test
    public void whenCarSizeIs1ThenNotParked() {
        boolean[] simplePlaces = {false, false, false};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        ParkMaster parkMaster = new TruckParkMaster();
        boolean[] expected = {false, false, false};
        parkMaster.parkCar(parking, 1);
        assertThat(parking.getSimplePlaces()).containsExactly(expected);
        assertThat(parking.getTruckPlaces()).containsExactly(expected);
    }

    @Test
    public void whenTruckParkingIsBusyParkToTheSimplePlace()  {
        boolean[] simplePlaces = {false, false, false};
        boolean[] truckPlaces = {true, true, true};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        ParkMaster parkMaster = new TruckParkMaster();
        boolean[] expectedSimplePlaces = {true, true, false};
        boolean[] expectedTruckPlaces = {true, true, true};
        parkMaster.parkCar(parking, 2);
        assertThat(parking.getSimplePlaces()).containsExactly(expectedSimplePlaces);
        assertThat(parking.getTruckPlaces()).containsExactly(expectedTruckPlaces);
    }

    @Test
    public void whenTruckParkingIsBusyThenException()  {
        boolean[] simplePlaces = {true, true, false};
        boolean[] truckPlaces = {true, true, true};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        ParkMaster parkMaster = new TruckParkMaster();
        assertThatThrownBy(() -> parkMaster.parkCar(parking, 2)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenParkingIsNullThenException() {
        Parking parking = null;
        ParkMaster parkMaster = new TruckParkMaster();
        assertThatThrownBy(() -> parkMaster.parkCar(parking, 2)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenInvalidSizeThenException() {
        boolean[] simplePlaces = {false, false, false};
        boolean[] truckPlaces = {false, false, false};
        Parking parking = new Parking(simplePlaces, truckPlaces);
        ParkMaster parkMaster = new TruckParkMaster();
        assertThatThrownBy(() -> parkMaster.parkCar(parking, 0)).isInstanceOf(IllegalArgumentException.class);
    }
}