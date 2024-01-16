package Leetcode;
//买卖股票的最佳时机
public class rehot45 {
    //直接用暴力法会超时
    //一次遍历
    public int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        //记录当前遇到的最低股票价格
        int min = Integer.MAX_VALUE;
        //记录最大利润
        int maxProfit = 0;
        for (int price : prices) {
            //遇到更低的价格就买入，再尝试之后每一天卖出并更新最大利润
            if (price <= min) {
                min = price;
            } else {
                maxProfit = Math.max(maxProfit, price - min);
            }
        }
        return maxProfit;
    }
}
