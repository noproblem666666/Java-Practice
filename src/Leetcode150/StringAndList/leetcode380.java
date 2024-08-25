package Leetcode150.StringAndList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
// O(1) 时间插入、删除和获取随机元素
public class leetcode380 {
    //哈希表加交换删除
    static int[] nums = new int[200010];
    Random random = new Random();
    Map<Integer, Integer> map = new HashMap<>();
    int idx = -1;
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        nums[++idx] = val;
        map.put(val, idx);
        return true;
    }
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int loc = map.remove(val);
        //Todo: 交换删除，把要最后一位元素交换移动到被删除元素的位置，这样下次增加的时候就会覆盖最后一位，同时更新数组边界和map中的下标
        //如果删除的就是数组的最后一位，就没必要更新map了，因为没有移动位置
        if (loc != idx) map.put(nums[idx], loc);
        nums[loc] = nums[idx--];
        return true;
    }
    public int getRandom() {
        return nums[random.nextInt(idx + 1)];
    }

}
