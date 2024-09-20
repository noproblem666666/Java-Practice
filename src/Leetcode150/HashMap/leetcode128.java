package Leetcode150.HashMap;

import java.util.HashSet;
import java.util.Set;

//最长连续序列
public class leetcode128 {
    //哈希表
    public int longestConsecutive(int[] nums) {

        int max = 0;
        //去重并且可以快速得知是否存在
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        //也可以使用map存储每个树为结尾的最大长度，然后对遍历到的每个数进行拼接
        for (int num : nums) {

            //只有在没有前缀的前提下计算才可能是最大长度
            //Todo：最重要的剪枝操作
            if(!set.contains(num-1)){
                int len = 1;
                int curr = num;
                //有后缀的情况下一直往后检查并尝试更新max
                while(set.contains(curr+1)){
                    len++;
                    curr++;
                }
                //max的比较放在while循环后一次即可
                max = Math.max(max,len);
            }
        }
        return max;
    }
}
