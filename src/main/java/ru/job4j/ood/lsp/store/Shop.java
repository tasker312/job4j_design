package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.utils.Validator;
import ru.job4j.ood.lsp.products.Food;

import java.time.LocalDate;

public class Shop extends AbstractStore {
    @Override
    public boolean validate(Food food) {
        double usage = Validator.getUsagePercent(food, LocalDate.now());
        return 25 <= usage && usage < 100;
    }

    @Override
    protected void discount(Food food) {
        double usage = Validator.getUsagePercent(food, LocalDate.now());
        if (usage >= 75) {
            food.setDiscount(20);
        }
        super.discount(food);
    }
}
