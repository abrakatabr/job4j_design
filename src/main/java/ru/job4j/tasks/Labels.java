package ru.job4j.tasks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Labels {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> temp = new HashMap<>();
        List<Integer> result = new LinkedList<>();
        int left = 0;
        int right = 0;
        int count = 1;
        int begin = 0;
        while (left < s.length() - 1) {
            if (temp.containsKey(left)) {
                left++;
                continue;
            }
            if (s.lastIndexOf(s.charAt(left)) > right) {
                right = s.lastIndexOf(s.charAt(left));
                count = right - begin + 1;
            }
            if (left == right) {
                begin = left + 1;
                result.add(count);
                count = 1;
            }
            temp.put(s.charAt(left), s.lastIndexOf(s.charAt(left)));
            left++;
            if (left == s.length() - 1) {
                result.add(count);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Labels labels = new Labels();
        String s = "eaaaabaaec";
        System.out.println(labels.partitionLabels(s));
    }
}
