package ru.job4j.ood.lsp.products;

import java.time.LocalDate;

public class Apple extends Food {

    public Apple(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }
}
