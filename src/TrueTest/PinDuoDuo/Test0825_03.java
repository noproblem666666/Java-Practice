package TrueTest.PinDuoDuo;

import java.util.*;
public class Test0825_03 {
    // Todo：因为只能换到价值更低的礼物，所以每个位置上的数只能变大。最优方案是替换掉不符合条件的最小的数，在此情况下排序对照有多少个位置需要交换即可。
    //该方法只通过了5%用例
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int x = in.nextInt();
        int[] temp = new int[n];
        for (int i = 0;i<n;i++) {
            temp[i] = in.nextInt();
        }
        //记录所有的逆序对，temp[i]>temp[i+1],记录i
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<n-1;i++){
            if(temp[i]>temp[i+1]){
                list.add(i);
            }
        }
        //从后往前检查
        Collections.reverse(list);
        int count = 0;
        for(Integer index : list){
            //此时不可能解决
            if(x<temp[index]){
                System.out.println(-1);
            }
            for(int i = index + 2;i<n;i++){
                if(temp[i]<x){
                    count++;
                    x = temp[index+2];
                }
            }
            //实际交换
            int m = x;
            x = temp[index+1];
            temp[index+1] = m;
            count++;
        }
        System.out.println(count);
    }


}
