package TrueTest.MeiTuan;
import java.util.*;
// 动态规划加逆向思维
public class ListDelete {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int x = scanner.nextInt();
            int[] arr = new int[n];
            //记录未出现的最小非负整数
            boolean[] min = new boolean[(int) n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            //动态规划：在数组删除几个单个数字前进行整个删除需要的花费
            int[] dp = new int[n + 1];
            //从前往后难以计算未出现的最小非负整数，从后往前规划
            dp[n] = x * n;
            //题目中的MEX(a)表示剩余的当前数组中未出现的最小非负整数，从后往前逐个记录非常方便
            int mex = 0;
            for (int i = n - 1; i >= 0; i--) {
                //标记的操作应该放在前面
                min[arr[i]] = true;
                int mexNow = mex;
                while (min[mexNow]) {
                    mexNow++;
                }
                //递归表达式
                dp[i] = dp[i + 1] - x - k * mex + k * mexNow;
                mex = mexNow;
            }
            int minCost = Integer.MAX_VALUE;
            for (int i : dp) {
                minCost = Math.min(minCost, i);
            }
            System.out.println(minCost);
            T--;
        }
        scanner.close();
    }

    //另一种动态规划方法，从后往前开始计算最小值，每一步都有两种策略，删第一个或删全部
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        while (T-- > 0) {
            long n = scanner.nextLong();
            long k = scanner.nextLong();
            long x = scanner.nextLong();

            long[] A = new long[(int) n];
            for (int i = 0; i < n; ++i) {
                A[i] = scanner.nextLong();
            }
            //表示从i到最后的数组，删除消耗的最小值
            long[] dp = new long[(int) (n + 1)];
            Arrays.fill(dp, Long.MAX_VALUE);
            dp[(int) n] = 0;

            Set<Long> vst = new HashSet<>();
            long suffix_MEX = 0;

            for (int i = (int) (n - 1); i >= 0; --i) {
                vst.add(A[i]);
                while (vst.contains(suffix_MEX)) {
                    suffix_MEX++;
                }
                //从后往前遍历，要不在上一步的基础上删第一个，要么选择全部删掉，取最小值
                dp[i] = Math.min(dp[i + 1] + x, k * suffix_MEX);
            }

            System.out.println(dp[0]);
        }

        scanner.close();
    }
}
