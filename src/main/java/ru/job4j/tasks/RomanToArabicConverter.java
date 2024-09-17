package ru.job4j.tasks;

import java.util.HashMap;
import java.util.Map;

public class RomanToArabicConverter {
    public int convert(String roman) {
        if (roman.length() == 0) {
            throw  new IllegalArgumentException();
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] chars = roman.toCharArray();
        int result = 0;
        char previous = chars[0];
        int count = 1;
        if (chars.length == 1) {
            result = map.get(chars[0]);
        } else {
            for (int i = 1; i < chars.length; i++) {
                if (map.get(chars[i]) > map.get(chars[i - 1])) {
                    result -= map.get(chars[i - 1]);
                    count = 1;
                } else {
                    result += map.get(chars[i - 1]);
                    if (chars[i] == previous) {
                        count++;
                    }
                }
                if (i == chars.length - 1) {
                    result += map.get(chars[i]);
                }
                if (count > 3) {
                    throw new IllegalArgumentException();
                }
            }
        }
        return result;
    }
}
