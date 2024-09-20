package TrueTest.WeiLai;

import java.util.*;

// 找出总分比一个人低但是单科比一个人高的人数
public class Test0905_01 {
    public static void main2(String[] args) {
        LinkedHashSet<int[]> set = new LinkedHashSet<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // 注意 hasNext 和 hasNextLine 的区别
        int[][] temp = new int[n][4];

        for(int i=0;i<n;i++){
            temp[i][1] = in.nextInt();
            temp[i][2] = in.nextInt();
            temp[i][3] = in.nextInt();
            temp[i][0] = temp[i][1]+temp[i][2]+temp[i][3];
        }
        List<int[]> ints = Arrays.stream(temp).sorted(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        }).toList();
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }

    }
//oj里面用tolist方法就会报错cannot find symble
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // 注意 hasNext 和 hasNextLine 的区别
        int[][] temp = new int[n][4];
        for(int i=0;i<n;i++){
            temp[i][1] = in.nextInt();
            temp[i][2] = in.nextInt();
            temp[i][3] = in.nextInt();
            temp[i][0] = temp[i][1]+temp[i][2]+temp[i][3];
        }
        Object[] k = Arrays.stream(temp).sorted(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        }).toArray();

        int count = 0;
        for(int i = 0;i<n;i++){
            for(int j = i+1;j<n;j++){
                int[] temp1 = (int[])k[i];
                int[] temp2 = (int[])k[j];
                if(temp1[0]==temp2[0]){
                    continue;
                }
                if(temp1[1]>temp2[1]||temp1[2]>temp2[2]||temp1[3]>temp2[3]){
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
