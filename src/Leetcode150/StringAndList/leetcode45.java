package Leetcode150.StringAndList;

import java.util.Arrays;

// 跳跃游戏 II
public class leetcode45 {
    //动态规划
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        //最后一位直接获得结果，不需要再往后计算
        for (int i = 0; i < dp.length - 1; i++) {
            int step = nums[i];
            //注意还需要判断是否会越界
            for(int j = 1;j<=step;j++){
                if(i+j>=n){
                    break;
                }
                dp[i+j] = Math.min(dp[i+j],dp[i]+1);
            }
        }
        return dp[n-1];
    }

    // 贪心算法，加入对步数的计算，每次走到上一步的边界都会刷新边界为最远处并将步数加一
    public int jump2(int[] nums) {
        // 记录当前能跳跃到的位置的边界下标
        int border = 0;
        // 记录在边界范围内，能跳跃的最远位置的下标
        int maxPosition = 0;
        // 记录所用步数
        int steps = 0;
        for(int i=0;i<nums.length-1;i++){
            // 继续往下遍历，统计边界范围内，哪一格能跳得更远，每走一步就更新一次能跳跃的最远位置下标
            // 其实就是在统计下一步的最优情况
            maxPosition = Math.max(maxPosition,nums[i]+i);
            // 如果到达了边界，那么一定要跳了，下一跳的边界下标就是之前统计的最优情况maxPosition，并且步数加1
            if(i==border){
                border = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
