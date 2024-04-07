package Leetcode;
//比特位计数
public class rehot82 {

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for(int i = 1; i <= num; i++){
            //按位与运算，奇数计算结果为1，偶数为0（比求余数快）
            if ((i & 1) == 0){
                result[i] = result[i >> 1];
            }else {
                result[i] = result[i - 1] + 1;
            }
        }
        return result;
    }

    public int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            //按位与运算来判断是不是2的次幂
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }


}
