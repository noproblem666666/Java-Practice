package Leetcode150.StringAndList;

import java.util.Arrays;

//删除有序数组中的重复项 II
public class leetcode80 {
    public int removeDuplicates(int[] nums) {
        int k = 2;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            //Todo:注意这里的条件设置，第一个防止数组越界，初始处理，第二个是index-k，不是i-k，要比较的是当前写入位置前的第k个元素
            if (i < k || nums[index - k] != nums[i]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    //双指针详细解法
    public int removeDuplicates2(int[] nums) {
        int k = 2; // 每个元素最多出现的次数
        if (nums.length <= k) return nums.length; // 如果数组长度小于等于 k，直接返回数组长度

        // 初始化慢指针和快指针
        int slow = k, fast = k;
        while (fast < nums.length) {
            // 如果 nums[fast] 不等于 nums[slow - k]
            // 则将 nums[fast] 复制到 nums[slow]，并将 slow 向前移动一位
            if (nums[fast] != nums[slow - k]) { //nums[slow - k] 是当前考虑的元素在新数组中的第一个可能的位置
                nums[slow] = nums[fast];
                slow++;
            }
            // 将 fast 向前移动一位，以检查下一个元素
            fast++;
        }
        // 返回新数组的长度
        return slow;
    }
}
