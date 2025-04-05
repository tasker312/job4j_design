package ru.job4j.ood.lsp.storage.store;

import ru.job4j.ood.lsp.storage.utils.Validator;
import ru.job4j.ood.lsp.storage.products.Food;

import java.time.LocalDate;

public class Trash extends AbstractStore {
    @Override
    public boolean validate(Food food) {
        double usage = Validator.getUsagePercent(food, LocalDate.now());
        return usage >= 100;
    }
}
