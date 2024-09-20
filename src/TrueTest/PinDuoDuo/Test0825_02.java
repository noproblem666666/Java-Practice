package TrueTest.PinDuoDuo;
import java.util.*;
public class Test0825_02 {
    public static void main(String[] args) {
        //注意，奇数与偶数相加必定为奇数，这是偶数变奇数最快的操作，之后这个奇数可以继续和别的偶数相加变为奇数
        //如果有奇数，让所有偶数依次和他相加就可以变成奇数了。没有奇数的话就先找一个除2最快变成奇数的偶数，变成奇数。
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        List<Integer> list = new ArrayList<>();
        while (T-->0) {
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
                //可以把偶数求2的n次方运算替换为(int)(Math.log(T)/Math.log(2)),换底公式，因为java中Math.log()默认以e为底
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
