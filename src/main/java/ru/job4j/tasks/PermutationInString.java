package ru.job4j.tasks;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        char[] s2Arr = s2.toCharArray();
        char[] s1Arr = s1.toCharArray();
        boolean result = false;
        for (char ch : s1Arr) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        Map<Character, Integer> tempMap = new HashMap<>(map);
        int begin = 0;
        for (int i = 0; i < s2Arr.length; i++) {
            if (tempMap.containsKey(s2Arr[i])) {
                tempMap.put(s2Arr[i], tempMap.get(s2Arr[i]) - 1);
                if (tempMap.get(s2Arr[i]) == 0) {
                    tempMap.remove(s2Arr[i]);
                }
            } else {
                tempMap = null;
                tempMap = new HashMap<>(map);
                i = begin;
                begin++;
            }
            if (tempMap.size() == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PermutationInString permutation = new PermutationInString();
        String s1 = "adc";
        String s2 = "dcda";
        System.out.println(permutation.checkInclusion(s1, s2));
    }
}
