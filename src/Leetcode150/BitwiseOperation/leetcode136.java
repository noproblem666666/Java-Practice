package Leetcode150.BitwiseOperation;
//只出现一次的数字
public class leetcode136 {
    //按位异或，两次的数抵消为0，一次的那个数被保留
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }
}
