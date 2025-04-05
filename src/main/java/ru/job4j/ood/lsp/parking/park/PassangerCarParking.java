package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.cars.Car;

public class PassangerCarParking extends AbstractParking {

    public PassangerCarParking(int capacity) {
        super(capacity);
    }

    @Override
    public Car findByNumber(String number) {
        return null;
    }
}
