package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Order {
    private double price;
    private String comment;
    private Person client;
    private Product[] products;
    private boolean isFinished;

    public Order(String comment, Person client, Product[] products) {
        this.comment = comment;
        this.client = client;
        this.products = products;
        Arrays.stream(products).forEach(product -> price += product.getPrice());
        isFinished = false;
    }

    @Override
    public String toString() {
        return "Order{"
                + "price=" + price
                + ", comment='" + comment + '\''
                + ", client=" + client
                + ", products=" + Arrays.toString(products)
                + ", isFinished=" + isFinished
                + '}';
    }

    public static void main(String[] args) {
        Order order = new Order("hello", new Person("Ivan", "88888888888"),
                new Product[]{new Product("Milk", 20.5), new Product("Chocolate", 40)});
        System.out.println(order);
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(order);
        Order newOrder = gson.fromJson(json, Order.class);
        System.out.println(newOrder);
        System.out.println(json);
    }
}
