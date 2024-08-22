package ru.job4j.algo.greedy;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int tank = 0;
        int start = 0;
        int result = -1;
        int currentStation = 0;

        for (int i = 0; i <= gas.length; i++) {
            totalGas += gas[currentStation];
            totalCost += cost[currentStation];
            tank += gas[currentStation] - cost[currentStation];
            if (tank < 0) {
                start++;
                if (start == gas.length) {
                    break;
                }
                currentStation = start;
                i = 0;
                tank = 0;
                totalGas = 0;
                totalCost = 0;
                continue;
            }
            currentStation++;
            if (currentStation == gas.length) {
                currentStation = 0;
            }
        }
        if (totalGas >= totalCost) {
           result = start;
        }
        return result;
    }
}
