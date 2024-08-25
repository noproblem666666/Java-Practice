package Leetcode100;
//寻找重复数（不修改数组且只使用o（1）的额外空间）
public class rehot74 {
    //看作是链表找环，找重复数即是找环的入口
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0;
        int pre2 = slow;
        while(pre1 != pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }

    //二分法
    public int findDuplicate2(int[] nums) {
        int len = nums.length; // n + 1 = len, n = len - 1

        // 在 [1..n] 查找 nums 中重复的元素
        int left = 1;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;

            // nums 中小于等于 mid 的元素的个数
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                // 下一轮搜索的区间 [left..mid]
                right = mid;
            } else {
                // 下一轮搜索的区间 [mid + 1..right]
                left = mid + 1;
            }
        }
        return left;
    }



}
