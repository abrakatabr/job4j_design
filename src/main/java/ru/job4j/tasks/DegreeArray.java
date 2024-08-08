package ru.job4j.tasks;

public class DegreeArray {
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[nums.length];
        int counter = nums.length - 1;
        while (left <= right) {
            if (Math.abs(nums[left]) >= Math.abs(nums[right])) {
                result[counter] = nums[left] * nums[left];
                counter--;
                left++;
            } else {
                result[counter] = nums[right] * nums[right];
                counter--;
                right--;
            }
        }
        return result;
    }
}
