package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.utils.Validator;
import ru.job4j.ood.lsp.products.Food;

import java.time.LocalDate;

public class Warehouse extends AbstractStore {

    @Override
    public boolean validate(Food food) {
        double usage = Validator.getUsagePercent(food, LocalDate.now());
        return usage < 25;
    }
}
