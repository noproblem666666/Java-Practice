package Leetcode150.DynamicProgramming;

import java.util.Arrays;

//零钱兑换
public class leetcode322 {
    //一维动态规划
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,-1);
        dp[0]=0;
        for(int i =1;i<amount+1;i++){
            for(int j =0;j<coins.length;j++){
                //必须确保前一步是可达的，才能进行这一步
                if(i-coins[j]>=0 && dp[i-coins[j]]!=-1){
                    //如果是-1说明之前还没有到达，不能用较小值比较
                    if(dp[i]==-1){
                        dp[i] = dp[i-coins[j]]+1;
                    }else{
                        dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                    }
                }
            }
        }
        return dp[amount];
    }

    //记忆化搜索
    public int coinChange2(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

}
