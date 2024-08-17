package ru.job4j.sort;

import java.util.Arrays;
import java.util.Comparator;

public class Intervals {
    public int[][] merge(int[][] intervals) {
        int index = 0;
        int[][] result = new int[intervals.length][2];
        if (intervals.length == 1) {
            result = intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] >= intervals[i + 1][0]) {
                intervals[i + 1][0] = intervals[i][0];
                if (intervals[i][1] > intervals[i + 1][1]) {
                    intervals[i + 1][1] = intervals[i][1];
                }
            } else {
                result[index] = intervals[i];
                index++;
            }
            if (i == intervals.length - 2) {
                result[index] = intervals[i + 1];
            }
        }
        result = Arrays.copyOfRange(result, 0, ++index);
        return result;
    }
}
