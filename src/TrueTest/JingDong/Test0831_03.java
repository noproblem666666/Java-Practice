package TrueTest.JingDong;

import java.util.Scanner;

public class Test0831_03 {
    public static int choose(boolean bigOrSmall,int[][] temp,int i,int j,int n){
        if(i==0){
            if(j==n-1){
                return temp[i+1][j];
            }else{
                return Math.min(temp[i+1][j],temp[i][j+1]);
            }
        }else{
            if(j==0){
                return 0;
            }
            if(j==n-1){
                return temp[i-1][j];
            }else{
                return Math.min(temp[i+1][j],temp[i][j+1]);
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int[][] temp = new int[2][n];
        for(int i=0;i<2;i++){
            for(int j = 0;j<n;j++){
                temp[i][j]=in.nextInt();
            }
        }
        int[][]dp = new int[2][n];
        dp[0][0] = temp[0][0];

        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}
