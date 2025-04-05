package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.cars.Car;

public interface Parking {

    boolean park(Car car);

    Car unpark(String number);
}
