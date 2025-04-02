package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class TemplateGeneratorTest {

    @Test
    public void whenKeyNotExist() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "This is a template.";
        assertThat(generator.produce(template, Map.of()))
                .isEqualTo("This is a template");
    }

    @Test
    public void whenTemplateContainsOneKey() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "This is a ${item1}.";
        Map<String, String> args = Map.of(
                "item1", "value1"
        );
        assertThat(generator.produce(template, args))
                .isEqualTo("This is a value1");
    }

    @Test
    public void whenTemplateContainsManyKeys() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "This is a ${item1}, ${item2} and ${item3}";
        Map<String, String> args = Map.of(
                "item1", "value1",
                "item2", "value2",
                "item3", "value3"
        );
        assertThat(generator.produce(template, args))
                .isEqualTo("This is a value1, value2 and value3");
    }

    @Test
    public void whenTemplateStartsWithKey() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "${item1} is here.";
        Map<String, String> args = Map.of(
                "item1", "value1"
        );
        assertThat(generator.produce(template, args))
                .isEqualTo("value1 is here.");
    }

    @Test
    public void whenTemplateEndsWithKey() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "Here is ${item1}";
        Map<String, String> args = Map.of(
                "item1", "value1"
        );
        assertThat(generator.produce(template, args))
                .isEqualTo("Here is value1");
    }

    @Test
    public void whenTemplateContainsOnlyKey() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "${item1}";
        Map<String, String> args = Map.of(
                "item1", "value1"
        );
        assertThat(generator.produce(template, args))
                .isEqualTo("value1");
    }

    @Test
    public void whenTemplateContainsManyIdenticalKeys() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "This is a ${item1} and ${item1}";
        Map<String, String> args = Map.of(
                "item1", "value1"
        );
        assertThat(generator.produce(template, args))
                .isEqualTo("This is a value1 and value1");
    }

    @Test
    public void whenTemplateContainsNonExistentMapKeysThenException() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "This is a ${item1} and ${item2}";
        Map<String, String> args = Map.of(
                "item1", "value1"
        );
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMapContainsNonExistentTemplateKeysThenException() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "This is a ${item1}";
        Map<String, String> args = Map.of(
                "item1", "value1",
                "item2", "value2"
        );
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTemplateAndMapContainsDifferentKeysThenException() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "This is a ${item1}";
        Map<String, String> args = Map.of(
                "item2", "value2"
        );
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
