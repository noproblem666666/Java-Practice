package Leetcode100;
//买卖股票的最佳时机含冷冻期
public class rehot78 {
    //由于卖出后有一天的冷冻期，因此对于每一天共有四种状态：
    //分别是，不持有今天卖出，不持有非今天卖出，持有今天买入，持有非今天买入

    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][4];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        dp[0][3] = -prices[0];
        for(int i = 1;i<prices.length;i++){
            dp[i][0] = Math.max(dp[i-1][2],dp[i-1][3]) + prices[i];
            dp[i][1] = Math.max(dp[i-1][0],dp[i-1][1]);    //非今天卖出，那可能是昨天或者更前天
            dp[i][2] = dp[i-1][1] - prices[i];       //由于冷冻期，今天买入的话，昨天必不可能卖出
            dp[i][3] = Math.max(dp[i-1][2],dp[i-1][3]);   //非今天买入，可能是昨天，也可能是更前天
        }
        //最后肯定是卖出的收益高
        return Math.max(dp[prices.length-1][0],dp[prices.length-1][1]);
    }

    //Todo：持有的两种状态其实不涉及冷冻期，所以可以合并成一种状态，状态转移方程需要稍微调整
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }


}
