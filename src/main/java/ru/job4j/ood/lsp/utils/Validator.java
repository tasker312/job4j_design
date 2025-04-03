package ru.job4j.ood.lsp.utils;

import ru.job4j.ood.lsp.products.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Validator {
    public static double getUsagePercent(LocalDate date1, LocalDate date2, LocalDate date3) {
        long totalDays = ChronoUnit.DAYS.between(date1, date2) + 1;
        long passedDays = ChronoUnit.DAYS.between(date1, date3);
        return 100.0 * passedDays / totalDays;
    }

    public static double getUsagePercent(Food food, LocalDate date3) {
        return getUsagePercent(food.getCreateDate(), food.getExpiryDate(), date3);
    }
}
