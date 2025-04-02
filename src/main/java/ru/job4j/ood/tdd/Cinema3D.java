package ru.job4j.ood.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return null;
    }

    @Override
    public Ticket buy(Account account, int row, int col, Calendar date) {
        return new Ticket3D();
    }

    @Override
    public void add(Session session) {
    }
}
