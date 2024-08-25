package Leetcode150.StringAndList;
//删除有序数组中的重复项（与27做法基本一致）
public class leetcode26 {
    //记录当前要移动到的坐标（也可记录前移位置）
    public int removeDuplicates(int[] nums) {
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            //Todo：改成 nums[i]!=nums[index-1] 也是正确的，思考下为什么？
            if(nums[i]!=nums[i-1]){
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    //双指针
    public int removeDuplicates2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    //双指针优化，避免原地无意义复制
    public int removeDuplicates3(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                if(q - p > 1){
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p + 1;
    }

    //Todo：通用解法，每个元素最多保留前k个
    public int removeDuplicates4(int[] nums) {
        return process(nums, 1);
    }
    int process(int[] nums, int k) {
        int idx = 0;
        for (int x : nums) {
            //idx < k 的条件可以保证数组不越界，前k个元素必定要保留
            if (idx < k || nums[idx - k] != x) nums[idx++] = x;
        }
        return idx;
    }


}
