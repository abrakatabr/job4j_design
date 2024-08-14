package ru.job4j.tasks;

import java.util.HashMap;
import java.util.Map;

public class Parentheses {
    public static boolean valid(char[] data) {
        Map<Character, Integer> map = new HashMap<>();
        boolean result = false;
        for (char ch : data) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
            if (map.containsKey(')') && !map.containsKey('(')
            || (map.containsKey(')') && map.containsKey('(')) && map.get(')') > map.get('(')) {
                break;
            }
        }
        if (map.get('(') == map.get(')')) {
            result = true;
        }
        return result;
    }
}
