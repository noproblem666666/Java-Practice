package TrueTest.MeiTuan;

import java.util.Scanner;

public class Test0817_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        long sum = 0;
        for(int i = 0;i<n;i++){
            a[i] = scanner.nextLong();
            sum+=a[i];
        }
        long avg = sum/n;
        long minMovies = 0;
        long need = 0;
        for(int i = 0;i<n;i++){
            need+=a[i] - avg;
            minMovies = Math.max(minMovies,Math.abs(need));
        }
        System.out.println(minMovies);
        scanner.close();
    }
}
