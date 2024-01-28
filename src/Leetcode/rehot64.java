package Leetcode;
//最大正方形(动态规划)
public class rehot64 {
    //不够高效，o（n的三次方）（但是比暴力法强点，n的四次方）
    public int maximalSquare(char[][] matrix) {

        int maxArea = 0;
        int[][] temp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < temp.length; i++) {
            if(matrix[i][0]=='1'){
                //有元素为1的时候，面积才至少为1
                maxArea = 1;
                temp[i][0] = 1;
            }
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = 1; j < temp[0].length; j++) {
                if(matrix[i][j]=='1'){
                    //有元素为1的时候，面积才至少为1
                    maxArea =1;
                    temp[i][j] = temp[i][j-1]+1;

                }
            }
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                System.out.print(temp[i][j]);
            }
            System.out.println();
        }
        for (int i = 1; i < temp.length; i++) {
            for (int j = 1; j < temp[0].length; j++) {
                int maxLen = temp[i][j];
                int area;
                for(int n = i-1;n>=0;n--){
                    maxLen = Math.min(maxLen,temp[n][j]);
                    area = Math.min(maxLen,i-n+1)*Math.min(maxLen,i-n+1);
                    maxArea = Math.max(area,maxArea);
                }
            }
        }
        return maxArea;
    }

    //Todo：动态规划
    public int maximalSquare2(char[][] matrix) {
        // base condition
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;

        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;

        // 相当于已经预处理新增第一行、第一列均为0
        int[][] dp = new int[height + 1][width + 1];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == '1') {
                    dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                }
            }
        }
        return maxSide * maxSide;
    }

}
