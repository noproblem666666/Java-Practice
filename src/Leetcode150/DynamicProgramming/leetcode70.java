package Leetcode150.DynamicProgramming;
//爬楼梯
public class leetcode70 {
    //类似于斐波那契数列（兔子问题）
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        //从第0个台阶开始爬，初始值也要为1
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
