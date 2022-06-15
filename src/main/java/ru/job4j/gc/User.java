package ru.job4j.gc;

public class User {
    /*
    total space for 1 object = 88 байт
     */

    /*
    заголовок - 12 байт
     */

    /*
    age(int) - 4 байта
     */
    private int age;

    /*
    String : total = 4+12+4+1+4+4+8+4+4+24=69 -> 72 байта
    Ссылка - 4 байта

    Заголовок - 12 байт
    value(ссылка на byte[]) - 4 байта
    coder(byte) - 1 байт
    hash(int) - 4 байта
    hashIsZero(boolean) - 4 байта
    serialVersionUID(long) - 8 байт
    COMPACT_STRINGS(boolean) - 4 байта
    serialPersistentFields(ссылка на ObjectStreamField[]) - 4 байта

    byte[] : total = 16 + len -> 24 байта
    Заголовок - 16 байт
    каждый символ - 1 байт
     */
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
