package TrueTest.WeiLai;

import java.util.Scanner;
//滑动窗口找总和最相近的子正整数组
public class Test0905_02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for(int i =0;i<n;i++){
            a[i] = in.nextInt();
        }
        for(int i =0;i<m;i++){
            b[i] = in.nextInt();
        }
        int sum_A = 0;
        for(int i =0;i<n;i++){
            sum_A+=a[i];
        }
        int left = 0;
        int right = 0;
        int res_left = 0;
        int res_right = 0;
        int sum_B=0;
        int min = sum_A;
        while(right<m){

            sum_B+=b[right];
            right++;
            if(min>Math.abs(sum_A-sum_B)){
                min = Math.abs(sum_A-sum_B);
                res_left = left;
                res_right = right;
            }
            while(sum_A<sum_B && left<right){
                sum_B-=b[left];
                left++;
                if(min>Math.abs(sum_A-sum_B)){
                    min = Math.abs(sum_A-sum_B);
                    res_left = left;
                    res_right = right;
                }

            }
        }
        for(int i =res_left;i<res_right;i++){
            System.out.print(b[i]+" ");
        }
    }
}
