package TrueTest.PinDuoDuo;
import java.util.*;
public class Test0825_01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int times = in.nextInt();
        List<Integer> list = new ArrayList<>();
        while (times-- > 0) {
            int n = in.nextInt();
            int[] index = new int[n];
            for (int i = 0; i < n; i++) {
                index[i] = in.nextInt();
            }
            int[] line = new int[n - 1];
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                in.nextInt();
                in.nextInt();
                line[i] = in.nextInt();
                sum += line[i];
            }
            Arrays.sort(line);
            int max = sum + index[0];
            int temp = max;
            for (int i = 1; i < n; i++) {
                sum -= line[i - 1];
                max = Math.max(max, sum + index[i]);
            }
            list.add(max);
        }
        for(Integer i:list){
            System.out.println(i);
        }
    }
}
