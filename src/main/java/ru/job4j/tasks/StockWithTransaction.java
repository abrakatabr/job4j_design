package ru.job4j.tasks;

public class StockWithTransaction {
    public int maxProfit(int[] prices, int fee) {

        int n = prices.length;
        if (prices == null || n == 0) {
        return 0;
    }
        int cash = 0;
        int hold = -prices[0];
        int oldCash = 0;
        for (int i = 1; i < n; i++) {
            oldCash = cash;
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, oldCash - prices[i]);
        }
        return cash;
    }
}
