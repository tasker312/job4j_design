package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.cars.Car;

public abstract class AbstractParking implements Parking {

    private final int capacity;
    private final Car[] space;

    public AbstractParking(int capacity) {
        this.capacity = capacity;
        this.space = new Car[capacity];
    }

    @Override
    public boolean park(Car car) {
        return false;
    }

    @Override
    public Car unpark(String number) {
        return null;
    }

    public Car findByNumber(String number) {
        return null;
    }

    public int getFreeSpace() {
        return 0;
    }
}
