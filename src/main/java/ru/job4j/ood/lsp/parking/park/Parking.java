package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.cars.Car;

import java.util.Optional;

public interface Parking {

    boolean park(Car car);

    Optional<Car> unpark(String number);

    boolean contains(String number);
}
