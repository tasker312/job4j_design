package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.storage.utils.Validator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ValidatorTest {

    @Test
    public void whenFullTime() {
        LocalDate now = LocalDate.now();
        LocalDate created = now.plusDays(-1);
        LocalDate expires = now.plusDays(100);
        assertThat(Validator.getUsagePercent(created, expires, now))
                .isLessThan(25);
    }

    @Test
    public void whenHalfTime() {
        LocalDate now = LocalDate.now();
        LocalDate created = now.plusDays(-5);
        LocalDate expires = now.plusDays(5);
        assertThat(Validator.getUsagePercent(created, expires, now))
                .isGreaterThan(25)
                .isLessThan(75);
    }

    @Test
    public void whenSaleTime() {
        LocalDate now = LocalDate.now();
        LocalDate created = now.plusDays(-10);
        LocalDate expires = now.plusDays(1);
        assertThat(Validator.getUsagePercent(created, expires, now))
                .isGreaterThan(75)
                .isLessThan(100);
    }

    @Test
    public void whenNoTime() {
        LocalDate now = LocalDate.now();
        LocalDate created = now.plusDays(-5);
        LocalDate expires = now.plusDays(-1);
        assertThat(Validator.getUsagePercent(created, expires, now))
                .isGreaterThanOrEqualTo(100);
    }

}
