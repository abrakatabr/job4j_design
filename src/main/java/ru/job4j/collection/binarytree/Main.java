package ru.job4j.collection.binarytree;

import java.util.HashMap;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeAVLMap<Integer, String> bst = new TreeAVLMap<>();
        bst.insert(2, "B");
        bst.insert(1, "A");
        bst.insert(10, "I");
        bst.insert(6, "F");
        bst.insert(14, "K");
        bst.insert(4, "D");
        bst.insert(8, "L");
        bst.insert(12, "U");
        bst.insert(16, "X");
        bst.insert(11, "P");
        bst.insert(9, "S");
        bst.insert(13, "V");
        bst.insert(15, "E");
        bst.insert(17, "Y");
        bst.insert(3, "M");
        bst.insert(5, "Q");
        bst.insert(7, "Z");
        bst.insert(7, "W");
        System.out.println(bst);
        bst.remove(6);
        System.out.println(bst);
        System.out.println(bst.get(7));
        bst.remove(2);
        System.out.println(bst);
        bst.remove(10);
        System.out.println(bst);
        for (String s : bst.values()) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (Integer integer : bst.keySet()) {
            System.out.print(integer + " ");
        }
    }
}
