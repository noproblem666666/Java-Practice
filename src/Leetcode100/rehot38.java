package Leetcode100;
//不同的二叉搜索树（动态规划）
public class rehot38 {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        //规定dp[0] = 1 ，方便计算
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < n + 1; i++)
            for(int j = 1; j < i + 1; j++)
                //遍历每一个结点为根的情况，此时左子树结点个数和右子树结点个数是确定的
                dp[i] += dp[j-1] * dp[i-j];

        return dp[n];
    }


}
