package Leetcode150.StringAndList;

import java.util.Arrays;

//买卖股票的最佳时机（只能买卖一次）
public class leetcode121 {
    //一次遍历：记录当前最小的价格和能获得的最大利润，不断更新
    public int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        //记录当前遇到的最低股票价格
        int min = Integer.MAX_VALUE;
        //记录最大利润
        int maxProfit = 0;
        for (int price : prices) {
            //遇到更低的价格就买入，再尝试每一天卖出
            if (price <= min) {
                min = price;
            } else {
                maxProfit = Math.max(maxProfit, price - min);
            }
        }
        return maxProfit;
    }

    //动态规划，记录当前能获得的最大利润和当前最低的价格（其实不用动态规划）
    //或者动态规划直接记录当前从开始到当前下标的数组最小值，之后价格数组和动态规划数组相减求最大值
    public int maxProfit2(int[] prices) {
        int[] dp = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if(prices[i]<prices[i-1]){
                min = Math.min(prices[i],min);
            }else{
                dp[i] = prices[i] - min;
            }
        }
        Arrays.sort(dp);
        System.out.println(Arrays.toString(dp));
        return dp[prices.length-1];
    }

}
