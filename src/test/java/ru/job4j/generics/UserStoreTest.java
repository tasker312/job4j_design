package ru.job4j.generics;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStoreTest {
    @Test
    public void findByIdWhenSingle() {
        UserStore userStore = new UserStore();
        User user = new User("1", "Ivan", 20);
        userStore.add(user);
        assertEquals(user, userStore.findById("1"));
    }

    @Test
    public void findByIdWhenMany() {
        UserStore userStore = new UserStore();
        User user1 = new User("1", "Ivan", 20);
        User user2 = new User("2", "Petr", 25);
        userStore.add(user1);
        userStore.add(user2);
        assertEquals(user1, userStore.findById("1"));
    }

    @Test
    public void findByIdEmpty() {
        UserStore userStore = new UserStore();
        User user1 = new User("1", "Ivan", 20);
        User user2 = new User("2", "Petr", 25);
        userStore.add(user1);
        userStore.add(user2);
        assertNull(userStore.findById("24"));
    }

    @Test
    public void delete() {
        UserStore userStore = new UserStore();
        userStore.add(new User("1", "Ivan", 20));
        assertTrue(userStore.delete("1"));
        assertNull(userStore.findById("1"));
    }

    @Test
    public void deleteNothing() {
        UserStore userStore = new UserStore();
        userStore.add(new User("1", "Ivan", 20));
        assertFalse(userStore.delete("22"));
    }

    @Test
    public void replace() {
        UserStore userStore = new UserStore();
        userStore.add(new User("1", "Ivan", 20));
        User user = new User("1", "Petr", 25);
        assertTrue(userStore.replace("1", user));
        assertEquals(user, userStore.findById("1"));
    }

    @Test
    public void replaceNothing() {
        UserStore userStore = new UserStore();
        userStore.add(new User("1", "Ivan", 20));
        User user = new User("1", "Petr", 25);
        assertFalse(userStore.replace("24", user));
    }
}
