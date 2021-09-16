
package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    private int size;

    public T pop() {
        T value = linked.deleteFirst();
        size--;
        return value;
    }

    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
