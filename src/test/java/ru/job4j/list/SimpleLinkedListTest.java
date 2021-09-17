package ru.job4j.list;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class SimpleLinkedListTest {
    private List<Integer> list;

    @Before
    public void setUp() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
    }

    @Test
    public void whenChangeSize() {
        assertEquals(2, list.size());
        list.add(3);
        assertEquals(3, list.size());
    }

    @Test
    public void whenAddAndGet() {
        assertThat(list.get(0), Is.is(1));
        assertThat(list.get(1), Is.is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        list.get(2);
    }

    @Test
    public void whenSet() {
        int oldValue = list.set(0, 10);
        assertEquals(1, oldValue);
        assertEquals(Integer.valueOf(10), list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetFromOutOfBoundThenExceptionThrown() {
        list.set(2, 2);
    }

    @Test
    public void whenRemoveFirst() {
        int removed = list.remove(0);
        assertEquals(1, removed);
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(2), list.get(0));
    }

    @Test
    public void whenRemoveMid() {
        list.add(3);
        int removed = list.remove(1);
        assertEquals(2, removed);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromOutOfBoundThenExceptionThrown() {
        list.remove(2);
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(1));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(2));
        assertThat(first.hasNext(), Is.is(false));

        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(1));
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(2));
        assertThat(second.hasNext(), Is.is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenGetIteratorAndAddThenException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(3);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyListIteratorWhenNextException() {
        List<Integer> emptyList = new SimpleLinkedList<>();
        emptyList.iterator().next();
    }
}
