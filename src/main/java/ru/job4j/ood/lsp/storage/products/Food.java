package ru.job4j.ood.lsp.storage.products;

import java.time.LocalDate;

public abstract class Food {
    private final String name;
    private final LocalDate createDate;
    private final LocalDate expiryDate;
    private final double price;
    private int discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        validate();
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public double getBasePrice() {
        return price;
    }

    public double getActualPrice() {
        return price * (1 - discount / 100D);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Invalid discount: " + discount);
        }
        this.discount = discount;
    }

    private void validate() {
        if (createDate.isAfter(expiryDate)) {
            throw new IllegalArgumentException(
                    String.format("Create date %s is after expiry date %s", createDate, expiryDate)
            );
        }
        if (price < 0) {
            throw new IllegalArgumentException("Invalid price below 0: " + price);
        }
    }
}
