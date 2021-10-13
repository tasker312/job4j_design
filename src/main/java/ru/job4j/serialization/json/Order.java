package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public double getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public Person getClient() {
        return client;
    }

    public Product[] getProducts() {
        return products;
    }

    public boolean isFinished() {
        return isFinished;
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
        usingGson(order);
        usingJson(order);
    }

    private static void usingGson(Order order) {
        System.out.println(order);
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(order);
        Order newOrder = gson.fromJson(json, Order.class);
        System.out.println(newOrder);
        System.out.println(json);
    }

    private static void usingJson(Order order) {
        System.out.println(new JSONObject(order));

        JSONObject jsonObject = new JSONObject()
                .put("price", order.price)
                .put("comment", order.comment)
                .put("client", new JSONObject()
                        .put("name", order.client.getName())
                        .put("phone", order.client.getPhone()))
                .put("products", new JSONArray()
                        .put(new JSONObject()
                                .put("name", order.products[0].getName())
                                .put("price", order.products[0].getPrice()))
                        .put(new JSONObject()
                                .put("name", order.products[1].getName())
                                .put("price", order.products[1].getPrice())))
                .put("isFinished", order.isFinished);
        System.out.println(jsonObject);

    }
}
