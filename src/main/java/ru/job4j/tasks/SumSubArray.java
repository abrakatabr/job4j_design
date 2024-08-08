package ru.job4j.tasks;

public class SumSubArray {
    public int subArraySum(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int count = 0;
        int sum = 0;
        while (left <= nums.length - 1) {
            if (left == right) {
                sum = nums[left];
            } else {
                sum += nums[right];
            }
            if (sum == k) {
                count++;
            }
            if (right == nums.length - 1) {
                left++;
                right = left;
                continue;
            }
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {6, 4, 3, 1};
        int k = 10;
        SumSubArray sumSubArray = new SumSubArray();
        System.out.println(System.currentTimeMillis());
        System.out.println(sumSubArray.subArraySum(nums, k));
        System.out.println(System.currentTimeMillis());
    }
}
