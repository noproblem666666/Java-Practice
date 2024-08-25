package Leetcode100;
//戳气球（动态规划，分治法）
public class rehot79 {
    //假设边界有两个不能戳破的值为1的气球，从最后一个可戳破的气球开始倒着考虑
    public int maxCoins(int[] nums) {
        int[] temp = new int[nums.length+2];
        temp[0] = 1;
        temp[temp.length-1] = 1;
        for(int i = 1;i<temp.length-1;i++){
            temp[i+1] = nums[i];
        }
        int[][] dp = new int[temp.length][temp.length];
        for(int len = 3;len<=temp.length;len++){
            for(int i = 0;i<=temp.length-len;i++){
                int res = 0;
                for(int k =i+1;k<i+len-1;k++){
                    int left = dp[i][k];
                    int right = dp[k][i+len-1];
                    res = Math.max(res,left+temp[i]*temp[k]*temp[i+len-1]+right);
                }
                dp[i][i+len-1] = res;
            }
        }
        return dp[0][temp.length-1];
    }
}
