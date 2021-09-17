package ru.job4j.it;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        var iter = list.listIterator(index);
        iter.add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        var iter = list.listIterator(index + 1);
        iter.add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        var iter = list.listIterator();
        while (iter.hasNext()) {
            if (filter.test(iter.next())) {
                iter.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        var iter = list.listIterator();
        while (iter.hasNext()) {
            if (filter.test(iter.next())) {
                iter.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        var iter = list.listIterator();
        while (iter.hasNext()) {
            if (elements.contains(iter.next())) {
                iter.remove();
            }
        }
    }
}
