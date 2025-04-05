package ru.job4j.ood.lsp.parking.cars;

public abstract class Car {

    private final int size;
    private final String number;

    protected Car(int size, String number) {
        this.size = size;
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public String getNumber() {
        return number;
    }
}
