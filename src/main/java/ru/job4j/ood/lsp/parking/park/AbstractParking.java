package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.cars.Car;

import java.util.Optional;

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
    public Optional<Car> unpark(String number) {
        return Optional.empty();
    }

    @Override
    public boolean contains(String number) {
        return false;
    }

    private int getIndexByNumber(String number) {
        return -1;
    }

    public int getFreeSpace() {
        return 0;
    }
}
