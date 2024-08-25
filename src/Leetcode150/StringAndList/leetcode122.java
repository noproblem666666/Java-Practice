package Leetcode150.StringAndList;
//买卖股票的最佳时机 II（可以多次买入卖出）
public class leetcode122 {
    //贪心算法
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(0,prices[i]-prices[i-1]);
        }
        return profit;
    }

    //动态规划，用二维数组记录两个状态（通用）
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // 0：持有现金
        // 1：持有股票
        // 状态转移：0 → 1 → 0 → 1 → 0 → 1 → 0
        int[][] dp = new int[len][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            // 这两行调换顺序也是可以的
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

}
