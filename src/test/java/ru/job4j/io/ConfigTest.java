package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Ivan"));
        assertThat(config.value("password"), is("123456"));
    }

    @Test
    public void whenPairWithCommentAndEmptyLine() {
        String path = "./data/pair_with_comment_and_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Ivan"));
        assertThat(config.value("password"), is("123456"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValuesWithoutKeys() {
        String path = "./data/values_without_keys.properties";
        Config config = new Config(path);
        config.load();
    }
}
