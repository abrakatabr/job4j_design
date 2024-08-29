package ru.job4j.collection.binarytree;

public class Main {
    public static void main(String[] args) {
        AvlTree<Integer> bst = new AvlTree<>();
        int[] array = new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 3, 5, 7};
        for (int i : array) {
            bst.insert(i);
        }
        System.out.println(bst);
    }
}
