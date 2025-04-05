package ru.job4j.ood.lsp.storage.products;

import java.time.LocalDate;

public class Banana extends Food {
    public Banana(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }
}
