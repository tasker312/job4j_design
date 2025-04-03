package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.products.Food;
import ru.job4j.ood.lsp.store.Store;

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
