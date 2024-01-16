package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//最长连续序列
public class rehot47 {
    //哈希表
    public int longestConsecutive(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int max = 1;
        //去重并且可以快速得知是否存在
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        //也可以使用map存储每个树为结尾的最大长度，然后对遍历到的每个数进行拼接
        for (int num : nums) {
            int len = 1;
            //只有在没有前缀的前提下计算才可能是最大长度
            if(!set.contains(num-1)){
                //有后缀的情况下一直往后检查并尝试更新max
                while(set.contains(num+1)){
                    len++;
                    num++;
                }
                //max的比较放在while循环后一次即可
                max = Math.max(max,len);
            }
        }
        return max;
    }
}
