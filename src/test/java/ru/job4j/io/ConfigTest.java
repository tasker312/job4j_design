package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Ivan");
        assertThat(config.value("password")).isEqualTo("123456");
    }

    @Test
    public void whenPairWithCommentAndEmptyLine() {
        String path = "./data/pair_with_comment_and_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Ivan");
        assertThat(config.value("password")).isEqualTo("123456");
    }

    @Test
    public void whenPairWithManyEquals() {
        String path = "./data/pair_with_many_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("password")).isEqualTo("123456=");
        assertThat(config.value("name")).isEqualTo("Ivan==1");
    }

    @Test
    public void whenPairWithoutKeys() {
        String path = "./data/pair_without_keys.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenPairWithoutValues() {
        String path = "./data/pair_without_values.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
