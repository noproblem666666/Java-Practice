package Leetcode150.HashMap;

import java.util.HashMap;
import java.util.Map;

//两数之和
public class leetcode1 {
    //用哈希表判断数组中是否存在着target-x的值，降低时间复杂度
    public int[] twoSum(int[] nums, int target) {
        //key存放数组中的值，便于查找，value存放下标值，便于返回答案
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        //即使出现相同的元素覆盖，也不会影响，要么直接返回相同的这两个，要么更新其下标
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
    //也可以排序后使用双指针，和167题一样，但是这样的话下标就会乱，本题要求返回下标，故不可用
}
