package Leetcode;
//乘积最大子数组（动态规划）
public class rehot54 {
    //因为涉及到了负负为正的情况，所以每个数组位置的状态都记录最大正数和最小负数
    public int maxProduct(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        //记录每个数组位置为结尾的乘积子数组的最大正数和最小负数
        int[][] dp = new int[nums.length][2];
        if(nums[0]>0){
            dp[0][0] = nums[0];
        }else{
            dp[0][1] = nums[0];
        }
        //防止长度只有1的数组的情况
        int max = nums[0];
        for (int i = 1; i < dp.length; i++) {
            //状态转移方程
            if(nums[i]>0){
                dp[i][0] = Math.max(dp[i-1][0]*nums[i],nums[i]);
                dp[i][1] = Math.min(dp[i-1][1]*nums[i],0);
            }else{
                dp[i][0] = Math.max(dp[i-1][1]*nums[i],0);
                dp[i][1] = Math.min(dp[i-1][0]*nums[i],nums[i]);
            }
            max = Math.max(max,dp[i][0]);
        }
        return max;
    }

    //优化空间复杂度
    public int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }


}
