package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.cars.Car;

public class PassengerCarParking extends AbstractParking {

    public PassengerCarParking(int capacity) {
        super(capacity);
    }

    @Override
    void parkTo(int index, Car car) {
        for (int i = index; i < index + car.getSize(); i++) {
            this.space[i] = car;
        }
    }

    @Override
    Car unparkFrom(int index) {
        Car car = this.space[index];
        int size = car.getSize();
        for (int i = index; i < index + size; i++) {
            this.space[i] = null;
        }
        return car;
    }

    @Override
    boolean validate(Car car) {
        return true;
    }

    @Override
    int getIndexForPark(Car car) {
        int size = car.getSize();
        if (this.getFreeSpace() < size) {
            return -1;
        }
        int start = 0;
        for (int i = 0; i < this.space.length; i++) {
            if (this.space[i] != null) {
                start = i + 1;
            }
            if (i - start + 1 == size) {
                return start;
            }
        }
        return -1;
    }
}
