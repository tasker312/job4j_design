package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.cars.Car;

public class TruckCarParking extends AbstractParking {

    public TruckCarParking(int capacity) {
        super(capacity);
    }

    @Override
    void parkTo(int index, Car car) {
        this.space[index] = car;
    }

    @Override
    Car unparkFrom(int index) {
        Car car = this.space[index];
        this.space[index] = null;
        return car;
    }

    @Override
    boolean validate(Car car) {
        return car.getSize() > 1;
    }

    @Override
    int getIndexForPark(Car car) {
        for (int i = 0; i < this.space.length; i++) {
            if (this.space[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
