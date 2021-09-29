package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SimpleMapTest {
    private SimpleMap<String, User> map;
    private final User user1 = new User("Ivan", 0, LocalDate.MIN);

    @Before
    public void setUp() {
        map = new SimpleMap<>();
    }

    @Test
    public void sizeChange() {
        assertEquals(0, map.size());
        map.put(user1.getName(), user1);
        assertEquals(1, map.size());
    }

    @Test
    public void putDifferent() {
        User user2 = new User("Petr", 2, LocalDate.MAX);
        assertTrue(map.put(user1.getName(), user1));
        assertTrue(map.put(user2.getName(), user2));
        assertEquals(2, map.size());
    }

    @Test
    public void putSame() {
        map.put(user1.getName(), user1);
        assertFalse(map.put(user1.getName(), user1));
        assertEquals(1, map.size());
    }

    @Test
    public void putMoreWhenInitialCapacity() {
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i), new User(String.valueOf(i), i, LocalDate.now()));
        }
        assertThat(map.size(), greaterThan(8));
    }

    @Test
    public void getExistingValue() {
        map.put(user1.getName(), user1);
        assertEquals(user1, map.get(user1.getName()));
    }

    @Test
    public void getNotExistingValue() {
        assertNull(map.get("Hello"));
    }

    @Test
    public void removeExistingKey() {
        map.put(user1.getName(), user1);
        assertTrue(map.remove(user1.getName()));
        assertNull(map.get(user1.getName()));
    }

    @Test
    public void removeNotExistingKey() {
        assertFalse(map.remove("Hello"));
    }

    @Test
    public void iteratorManyHasNext() {
        map.put(user1.getName(), user1);
        User user2 = new User("Petr", 2, LocalDate.MAX);
        map.put(user2.getName(), user2);
        var iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals(user1.getName(), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(user2.getName(), iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNoSuchElementException() {
        var iterator = map.iterator();
        assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorConcurrentModificationException() {
        var iterator = map.iterator();
        map.put(user1.getName(), user1);
        iterator.hasNext();
    }
}
