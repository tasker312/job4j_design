package ru.job4j.ood.lsp.storage.store;

import java.util.List;

public interface Store<T> {

    boolean add(T t);

    List<T> getAll();
}
