package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        Iterator<ArrayList<Integer>> lists = nodes.iterator();
        while (source.hasNext()) {
            if (lists.hasNext()) {
                lists.next().add(source.next());
            } else {
                lists = nodes.iterator();
                lists.next().add(source.next());
            }
        }
    }
}
