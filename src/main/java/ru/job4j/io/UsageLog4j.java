package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short sh = 2;
        int i = 3;
        long lon = 4;
        float fl = 6f;
        double db = 7d;
        char ch = '5';
        boolean bool = true;
        LOG.debug("byte: {}, short: {}, int: {}, long: {}", b, sh, i, lon);
        LOG.debug("float: {}, double: {}", fl, db);
        LOG.debug("char: {}", ch);
        LOG.debug("boolean: {}", bool);
    }
}
