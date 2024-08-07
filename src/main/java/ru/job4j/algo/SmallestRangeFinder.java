package ru.job4j.algo;

import java.util.Arrays;
public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int[] result = null;
        int left = 0;
        int right = k - 1;
        while (right > left && right <= nums.length - 1) {
            if (nums[right] == nums[right - 1]) {
                left++;
                right = left + k - 1;
                continue;
            }
            right--;
            if (left == right) {
                result = new int[] {left, left + k - 1};
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
