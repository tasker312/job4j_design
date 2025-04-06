package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.cars.Car;
import ru.job4j.ood.lsp.parking.park.Parking;

import java.util.List;
import java.util.Optional;

public class ControlParking {
    private final List<Parking> parks;

    public ControlParking(List<Parking> parks) {
        this.parks = parks;
    }

    public boolean park(Car car) {
        return false;
    }

    public Optional<Car> unpark(String number) {
        return Optional.empty();
    }

    public int getFreeSpace() {
        return 0;
    }
}
