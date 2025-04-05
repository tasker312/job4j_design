package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.storage.products.Food;
import ru.job4j.ood.lsp.storage.store.Store;

import java.util.List;

public class ControlQuality {
    private final List<Store<Food>> stores;

    public ControlQuality(List<Store<Food>> stores) {
        this.stores = stores;
    }

    public Store<Food> insert(Food item) {
        return stores.stream()
                .filter(store -> store.add(item))
                .findFirst()
                .orElse(null);
    }
}
