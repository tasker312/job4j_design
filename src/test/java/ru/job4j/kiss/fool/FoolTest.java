package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    public void whenNumber() {
        int value = 1;
        assertThat(Fool.getCurrentAnswer(value)).isEqualTo("1");
    }

    @Test
    public void whenFizz() {
        int value = 3;
        assertThat(Fool.getCurrentAnswer(value)).isEqualTo("Fizz");
    }

    @Test
    public void whenBuzz() {
        int value = 5;
        assertThat(Fool.getCurrentAnswer(value)).isEqualTo("Buzz");
    }

    @Test
    public void whenFizzBuzz() {
        int value = 15;
        assertThat(Fool.getCurrentAnswer(value)).isEqualTo("FizzBuzz");
    }

}
