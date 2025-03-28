package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("test1=test2", "test3=test4");
        var map = nameLoad.getMap();
        assertThat(map.get("test1"))
                .isNotEmpty()
                .isEqualTo("test2");
        assertThat(map.get("test3"))
                .isNotEmpty()
                .isEqualTo("test4");
    }

    @Test
    void checkParseInvalid() {
        NameLoad nameLoad = new NameLoad();
        String test = "test1";
        assertThatThrownBy(() -> nameLoad.parse(test))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain")
                .hasMessageContaining("=")
                .hasMessageContaining(test);
    }

    @Test
    void checkWithNoKey() {
        NameLoad nameLoad = new NameLoad();
        String test = "=test1";
        assertThatThrownBy(() -> nameLoad.parse(test))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a key")
                .hasMessageContaining(test);
    }

    @Test
    void checkWithNoValue() {
        NameLoad nameLoad = new NameLoad();
        String test = "test1=";
        assertThatThrownBy(() -> nameLoad.parse(test))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a value")
                .hasMessageContaining(test);
    }
}
