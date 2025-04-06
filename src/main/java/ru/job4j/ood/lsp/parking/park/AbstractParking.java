package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.cars.Car;

import java.util.Optional;

public abstract class AbstractParking implements Parking {

    final Car[] space;

    public AbstractParking(int capacity) {
        this.space = new Car[capacity];
    }

    @Override
    public final boolean park(Car car) {
        if (!validate(car)) {
            return false;
        }
        int index = getIndexForPark(car);
        if (index == -1) {
            return false;
        }
        parkTo(index, car);
        return true;
    }

    @Override
    public Optional<Car> unpark(String number) {
        int index = getIndexByNumber(number);
        if (index == -1) {
            return Optional.empty();
        }
        return Optional.of(unparkFrom(index));
    }

    @Override
    public boolean contains(String number) {
        return getIndexByNumber(number) != -1;
    }

    abstract boolean validate(Car car);

    abstract int getIndexForPark(Car car);

    abstract void parkTo(int index, Car car);

    abstract Car unparkFrom(int index);

    int getIndexByNumber(String number) {
        if (number == null) {
            return -1;
        }
        for (int i = 0; i < space.length; i++) {
            if (space[i] != null && space[i].getNumber().equals(number)) {
                return i;
            }
        }
        return -1;
    }

    public int getFreeSpace() {
        int freeSpace = 0;
        for (Car car : space) {
            if (car == null) {
                freeSpace++;
            }
        }
        return freeSpace;
    }
}
