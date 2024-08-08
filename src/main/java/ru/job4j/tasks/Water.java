package ru.job4j.tasks;

public class Water {
    public int maxArea(int[] height) {
        int right = height.length - 1;
        int left = 0;
        int minHeight = 0;
        int range = 0;
        int square = 0;
        int maxSquare = 0;
        while (left < height.length - 1) {
            minHeight = Math.min(height[left], height[right]);
            range = right - left;
            square = minHeight * range;
            if (square > maxSquare) {
                maxSquare = square;
            }
            if (height[left] * range < maxSquare || left == right) {
                left++;
                right = height.length - 1;
                continue;
            }
            right--;
        }
        return  maxSquare;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        Water water = new Water();
        System.out.println(water.maxArea(height));
    }
}
