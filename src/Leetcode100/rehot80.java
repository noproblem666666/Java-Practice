package Leetcode100;

import java.util.Arrays;

//零钱兑换
public class rehot80 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i =1;i<amount+1;i++){
            for(int j =0;j<coins.length;j++){
                if(i-coins[j]>=0 && dp[i-coins[j]]!=Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        if(dp[amount]==Integer.MAX_VALUE){
            return -1;
        }
        return dp[amount];
    }
}
