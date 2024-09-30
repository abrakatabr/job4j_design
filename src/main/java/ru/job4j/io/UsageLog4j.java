package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        char character = 'A';
        byte byteNumber = 15;
        short shortNumber = 350;
        int intNumber = 40000;
        long longNumber = 3000000000L;
        float floatNumber = 3.345F;
        double doubleNumber = 25.543;
        boolean trueOrFalse = true;
        LOG.info("Char: {}, byte: {}, short: {}, int: {}, long: {}, float: {}, double: {}, boolean: {}",
                character, byteNumber, shortNumber, intNumber, longNumber, floatNumber, doubleNumber, trueOrFalse);
    }
}
