package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.products.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store<Food> {
    protected List<Food> products;

    public AbstractStore() {
        products = new ArrayList<>();
    }

    @Override
    public final boolean add(Food food) {
        if (validate(food)) {
            discount(food);
            products.add(food);
            return true;
        }
        return false;
    }

    @Override
    public final List<Food> getAll() {
        return products;
    }

    abstract public boolean validate(Food food);

    protected void discount(Food food) {
    }
}
