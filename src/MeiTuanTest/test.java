package MeiTuanTest;

import java.util.Scanner;

public class test {



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            count[i] = in.nextInt();
        }
        boolean flag_sum = true;
        int index = n - 1;
        int sum = 0;
        while (flag_sum) {
            flag_sum = false;
            index = n-1;
            //尝试所有能做的操作二
            while (index - 2 >= 0) {
                if (index < n && count[index - 2] > count[index]) {
                    int temp = count[index - 2];
                    count[index - 2] = count[index];
                    count[index] = temp;
                    index = index + 3;
                }
                index --;
            }

            //尝试进行一次操作一
            index = n - 1;
            while (index - 1 >= 0) {
                // System.out.println("333");
                if (count[index - 1] > count[index]) {
                    int temp = count[index - 1];
                    count[index - 1] = count[index];
                    count[index] = temp;
                    sum++;
                    flag_sum = true;
                    break;
                }
                index--;
            }
        }
        System.out.println(sum);

    }
}
