package ru.job4j.algo.hash;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        Map<Character, Integer> map = new HashMap();
        int maxSize = 0;
        int begin = 0;
        int beginOfMax = 0;
        String resultString = "";
        for (int i = begin; i < str.length(); i++) {
            Character tempChar = str.charAt(i);
            if (map.containsKey(tempChar)) {
                map.clear();
                begin++;
                i = begin - 1;
            } else {
             map.put(tempChar, i);
                if (map.size() > maxSize) {
                    maxSize = map.size();
                    beginOfMax = begin;
                }
            }
        }
        if (maxSize > 0) {
            resultString = str.substring(beginOfMax, beginOfMax + maxSize);
        }
        return resultString;
    }
}
