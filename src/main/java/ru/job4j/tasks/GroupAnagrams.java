package ru.job4j.tasks;

import java.util.*;
import static java.util.Arrays.sort;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, Integer> indexOfList = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        String[] tempStr = new String[strs.length];
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            tempStr[i] = String.valueOf(temp);
        }
        int index = 0;
        for (int i = 0; i < tempStr.length; i++) {
            if (indexOfList.containsKey(tempStr[i])) {
                result.get(indexOfList.get(tempStr[i])).add(strs[i]);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(strs[i]);
                result.add(newList);
                indexOfList.put(tempStr[i], index);
                index++;
            }
        }
        return result;
    }
}
