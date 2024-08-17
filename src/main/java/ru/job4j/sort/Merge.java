package ru.job4j.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        int[] result = new int[left.length + right.length];

        while (leftIndex < left.length || rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex] = left[leftIndex];
                resultIndex++;
                leftIndex++;
            } else {
                result[resultIndex] = right[rightIndex];
                resultIndex++;
                rightIndex++;
            }
            if (leftIndex == left.length) {
                while (rightIndex < right.length) {
                    result[resultIndex] = right[rightIndex];
                    resultIndex++;
                    rightIndex++;
                }
            }
            if (rightIndex == right.length) {
                while (leftIndex < left.length) {
                    result[resultIndex] = left[leftIndex];
                    resultIndex++;
                    leftIndex++;
                }
            }
        }
        return result;
    }
}
