package TrueTest.PinDuoDuo;
import java.util.*;
public class Test0825_02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int T = in.nextInt();
        List<Integer> list = new ArrayList<>();
        while (T-->0) { // 注意 while 处理多个 case
            int n = in.nextInt();
            Long[] temp = new Long[n];
            for(int i=0;i<n;i++){
                temp[i] = in.nextLong();
            }
            //偶数数量
            int sum_not_ji = 0;
            //数组中某个数转为奇数的最小次数
            int min = Integer.MAX_VALUE;
            for(int i =0;i<n;i++){
                Long j = temp[i];
                int count = 0;
                while(j%2==0){
                    j = j/2;
                    count++;
                    //超出最小值后已经没有继续计算的必要
                    if(count>min){
                        break;
                    }
                }
                min = Math.min(min,count);
                if(count!=0){
                    sum_not_ji++;
                }
            }
            if(min!=0){
                //没有奇数的情况，已经将一个偶数转化为奇数，所以需要-1
                list.add(min+sum_not_ji-1);
            }else{
                list.add(sum_not_ji);
            }
        }
        for(Integer i:list){
            System.out.println(i);
        }
    }
}
