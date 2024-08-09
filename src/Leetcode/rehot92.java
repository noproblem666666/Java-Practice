package Leetcode;

import java.util.*;

//目标和
public class rehot92 {
    //DFS
    public int findTargetSumWays(int[] nums, int t) {
        return dfs(nums, t, 0, 0);
    }
    int dfs(int[] nums, int t, int u, int cur) {
        if (u == nums.length) {
            return cur == t ? 1 : 0;
        }
        int left = dfs(nums, t, u + 1, cur + nums[u]);
        int right = dfs(nums, t, u + 1, cur - nums[u]);
        return left + right;
    }

    //记忆化搜索
    public int findTargetSumWays2(int[] nums, int t) {
        return dfs(nums, t, 0, 0);
    }
    Map<String, Integer> cache = new HashMap<>();
    int dfs2(int[] nums, int t, int u, int cur) {
        String key = u + "_" + cur;
        if (cache.containsKey(key)) return cache.get(key);
        if (u == nums.length) {
            cache.put(key, cur == t ? 1 : 0);
            return cache.get(key);
        }
        int left = dfs2(nums, t, u + 1, cur + nums[u]);
        int right = dfs2(nums, t, u + 1, cur - nums[u]);
        cache.put(key, left + right);
        return cache.get(key);
    }

    //动态规划
    public int findTargetSumWays3(int[] nums, int t) {
        int n = nums.length;
        int s = 0;
        for (int i : nums) s += Math.abs(i);
        if (Math.abs(t) > s) return 0;
        int[][] f = new int[n + 1][2 * s + 1];
        f[0][0 + s] = 1;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = -s; j <= s; j++) {
                if ((j - x) + s >= 0) f[i][j + s] += f[i - 1][(j - x) + s];
                if ((j + x) + s <= 2 * s) f[i][j + s] += f[i - 1][(j + x) + s];
            }
        }
        return f[n][t + s];
    }

    //转化为背包问题
    public int findTargetSumWays4(int[] nums, int t) {
        int n = nums.length;
        int s = 0;
        for (int i : nums) s += Math.abs(i);
        if (t > s || (s - t) % 2 != 0) return 0;
        int m = (s - t) / 2;
        int[][] f = new int[n + 1][m + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j <= m; j++) {
                f[i][j] += f[i - 1][j];
                if (j >= x) f[i][j] += f[i - 1][j - x];
            }
        }
        return f[n][m];
    }

    //转化为背包问题，一维从后往前
    public int findTargetSumWays5(int[] nums, int target) {
        /*
        类01背包问题:
        设pos为取+的数字和,neg为取-的数字和(均为正数),则target=pos-neg=pos-(sum-pos)=2*pos-sum
        故pos=(sum+target)/2>=0且为常数,因此此问题等价于求有多少种方式用nums[i]凑成和为pos
        进而该问题抽象为:用价值与体积均为nums[i]的物品,恰好凑满容量为pos的背包方案数
        1.状态定义:dp[j]为恰好能凑满容量为j的背包方案数
        2.状态转移:背包容量能或者不能装下nums[i]
            2.1 当不能装下nums[i]时,方案数直接继承之前的dp[j]
            2.2 当能装下nums[i]时,总的方案数为不考虑nums[i]的方案数+有nums[i]参与新增的方案数
                dp[j] += dp[j - nums[i]],dp[j - nums[i]]种方案与nums[i]共同凑成pos,即1*dp[j - nums[i]]
        3.状态初始化:dp[0]=1,因为后面总会一直查找至j=0,如dp[3] += dp[3-3],空集是任意一条有效路径的起点,当属一条
        4.遍历顺序:i正序,j倒序
        5.返回形式:dp[pos]就是凑成pos总的方案数
         */
        int len = nums.length;
        int sum = 0;
        for (int num : nums) sum += num;
        // pos为小数||target绝对值比sum还大
        if ((sum + target) % 2 == 1 || Math.abs(target) > sum) return 0;
        int pos = (sum + target) / 2;
        int[] dp = new int[pos + 1];
        dp[0] = 1;
        for (int num : nums) {
            //从后往前
            for (int j = pos; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[pos];
    }


}
