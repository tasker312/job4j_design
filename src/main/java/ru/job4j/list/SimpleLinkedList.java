package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private int modCount;

    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        if (first == null) {
            first = new Node<>(null, value, null);
            last = first;
        } else {
            Node<E> oldLast = last;
            last = new Node<>(oldLast, value, null);
            oldLast.next = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E set(int index, E newValue) {
        Node<E> node = nodeByIndex(index);
        E oldValue = node.value;
        node.value = newValue;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        Node<E> node = nodeByIndex(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }
        size--;
        modCount++;
        return node.value;
    }

    @Override
    public E get(int index) {
        Node<E> node = nodeByIndex(index);
        return node.value;
    }

    @Override
    public int size() {
        return size;
    }

    private Node<E> nodeByIndex(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private Node<E> current = first;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        private E value;
        private Node<E> prev;
        private Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
