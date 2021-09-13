package ru.job4j.generics;

public class User extends Base {
    private String name;
    private int age;

    public User(String id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
