package ru.job4j.ood.lsp.storage.products;

import java.time.LocalDate;

public class Pie extends Food {
    public Pie(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }
}
