package ru.job4j.list;

public class Node<E> {
    E value;
    Node<E> prev;
    Node<E> next;

    public Node(Node<E> prev, E value, Node<E> next) {
        this.prev = prev;
        this.value = value;
        this.next = next;
    }
}
