package Leetcode150.StringAndList;

import java.util.Arrays;

//跳跃游戏
public class leetcode55 {
    //贪心算法，遍历数组，每次都计算可以到达的最远处，只要能够到达最远端就算成功
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            //只要i<=rightmost,就必定能到达i
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    //动态规划，记录下标是否能到达(其实简化之后发现只需要记录当前能到达的最右边即可)
    public boolean canJump2(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 0; i < dp.length; i++) {
            if(dp[i]){
                //防止越界，如果填到了最后一位直接返回true
                if(i+nums[i]>=nums.length-1){
                    return true;
                }
                //一个个填浪费时间
                //需要加一，toIndex的下标是不会被填的
                Arrays.fill(dp,i,i+nums[i]+1,true);
            }else{
                return false;
            }
        }
        return dp[nums.length-1];
    }
}
