package TrueTest.DaJiang;

import java.util.Scanner;

public class RobotElectric {
    //网格问题（反向思维），长宽分辨对结果不影响
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
        int res = 0;
        //表示每个到达网格时最高的电量，起点为0，0的初始值
        int[][] dp = new int[m][n];
        //指示路径，1表示该路径是从上来的，-1表示该路径是从左来的
        //Todo：这样做是错误的，如果遇到从上和从左的数字一样的情况，还需要分多条路径计算其最小值（需要用到递归，最后选出最大的一个最小值）
        //Todo：不如直接从右下角开始往上遍历，模拟其回到触发点时所需的最少初始电量
        int[][] point = new int[m][n];
        //由于机器人在行走的过程中电量降到0的时候就会爆炸，所以必须在最后选择的路径中计算其会到达的最小值，最后返回的是最小值的相反数再加一（最小值如果为负数）
        dp[0][0] = grid[0][0];

        int min = dp[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
            min = Math.min(min, dp[i][0]);
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
            min = Math.min(min, dp[0][j]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                min = Math.min(min, dp[i][j]);
            }
        }
        if (min >= 0) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }
    }

    /* Write Code Here */
    public int calcMinimumPower(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[m - 1][n - 1] = Math.max(1, 1 - grid[m - 1][n - 1]);

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i < m - 1) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(1, dp[i + 1][j] - grid[i][j]));
                }
                if (j < n - 1) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(1, dp[i][j + 1] - grid[i][j]));
                }
            }
        }

        return dp[0][0];

    }
}

class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;

        int grid_rows = 0;
        int grid_cols = 0;
        grid_rows = in.nextInt();
        grid_cols = in.nextInt();

        int[][] grid = new int[grid_rows][grid_cols];
        for (int grid_i = 0; grid_i < grid_rows; grid_i++) {
            for (int grid_j = 0; grid_j < grid_cols; grid_j++) {
                grid[grid_i][grid_j] = in.nextInt();
            }
        }

        if (in.hasNextLine()) {
            in.nextLine();
        }

        res = new RobotElectric().calcMinimumPower(grid);
        System.out.println(String.valueOf(res));

    }
}