package Leetcode;
//完全平方数（动态规划）Todo：和零钱兑换是一个道理
public class rehot72 {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i = 1;i<dp.length;i++){
            int j = 1;
            dp[i] = i;
            while(i-j*j>=0){
                //最坏情况就是都由1组成，之后找越来越大的平方数尝试进行减少数量
                dp[i] = Math.min(dp[i],dp[i-j*j]+1);
                j++;
            }
        }
        return dp[n];
    }
}
