package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        var parentNode = findBy(parent);
        if (parentNode.isPresent() && findBy(child).isEmpty()) {
            parentNode.get().getChildren().add(new Node<>(child));
            return true;
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(eNode -> eNode.getValue().equals(value));
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }

    public boolean isBinary() {
        return findByPredicate(eNode -> eNode.getChildren().size() > 2).isEmpty();
    }
}
