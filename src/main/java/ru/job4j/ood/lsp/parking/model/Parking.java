package ru.job4j.ood.lsp.parking.model;

public class Parking {
    private boolean[] simplePlaces;
    private boolean[] truckPlaces;

    public Parking(int simplePlacesSize, int truckPlacesSize) {
        this.simplePlaces = new boolean[simplePlacesSize];
        this.truckPlaces = new boolean[truckPlacesSize];
    }

    public Parking(boolean[] simplePlaces, boolean[] truckPlaces) {
        this.simplePlaces = simplePlaces;
        this.truckPlaces = truckPlaces;
    }

    boolean isFree(int size) {
        return false;
    }

    public boolean[] getSimplePlaces() {
        return simplePlaces;
    }

    public void setSimplePlaces(boolean[] simplePlaces) {
        this.simplePlaces = simplePlaces;
    }

    public boolean[] getTruckPlaces() {
        return truckPlaces;
    }

    public void setTruckPlaces(boolean[] truckPlaces) {
        this.truckPlaces = truckPlaces;
    }
}
