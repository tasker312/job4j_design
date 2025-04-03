package ru.job4j.ood.lsp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.Apple;
import ru.job4j.ood.lsp.products.Banana;
import ru.job4j.ood.lsp.products.Food;
import ru.job4j.ood.lsp.products.Pie;
import ru.job4j.ood.lsp.store.Shop;
import ru.job4j.ood.lsp.store.Store;
import ru.job4j.ood.lsp.store.Trash;
import ru.job4j.ood.lsp.store.Warehouse;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {

    ControlQuality controller;
    List<Store<Food>> stores;
    Store<Food> warehouse;
    Store<Food> shop;
    Store<Food> trash;

    @BeforeEach
    public void setUp() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        stores = List.of(warehouse, shop, trash);
        controller = new ControlQuality(stores);
    }

    @Test
    public void whenInsertIntoWarehouse() {
        Food apple = new Apple(
                "Apple1",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                20
        );
        controller.insert(apple);
        assertThat(warehouse.getAll())
                .containsExactly(apple);
    }

    @Test
    public void whenInsertIntoStore() {
        Food apple = new Apple(
                "Apple1",
                LocalDate.now().plusDays(-5),
                LocalDate.now().plusDays(5),
                20
        );
        controller.insert(apple);
        assertThat(shop.getAll())
                .containsExactly(apple);
    }

    @Test
    public void whenInsertIntoStoreWithSale() {
        Food apple = new Apple(
                "Apple1",
                LocalDate.now().plusDays(-10),
                LocalDate.now().plusDays(1),
                20
        );
        controller.insert(apple);
        assertThat(shop.getAll())
                .containsExactly(apple);
        assertThat(apple.getActualPrice())
                .isLessThan(apple.getBasePrice());
    }

    @Test
    public void whenInsertIntoTrash() {
        Food apple = new Apple(
                "Apple1",
                LocalDate.now().plusDays(-5),
                LocalDate.now().plusDays(-1),
                20
        );
        controller.insert(apple);
        assertThat(trash.getAll())
                .containsExactly(apple);
    }

    @Test
    public void whenInsertManyFoods() {
        Food apple = new Apple(
                "Apple1",
                LocalDate.now().plusDays(-10),
                LocalDate.now().plusDays(1),
                20
        );
        Food banana = new Banana(
                "Banana1",
                LocalDate.now().plusDays(-10),
                LocalDate.now().plusDays(10),
                50
        );
        Food pie = new Pie(
                "Pie1",
                LocalDate.now().plusDays(-10),
                LocalDate.now().plusDays(1),
                100
        );
        controller.insert(apple);
        controller.insert(banana);
        controller.insert(pie);
        assertThat(shop.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(apple, banana, pie);
        assertThat(apple.getActualPrice())
                .isLessThan(apple.getBasePrice());
        assertThat(banana.getActualPrice())
                .isEqualTo(banana.getBasePrice());
        assertThat(pie.getActualPrice())
                .isLessThan(pie.getBasePrice());
    }
}
