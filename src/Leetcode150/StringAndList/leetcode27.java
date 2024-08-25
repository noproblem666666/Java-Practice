package Leetcode150.StringAndList;
//移除元素（将不等于val的元素放在数组前k个位置并返回k）
public class leetcode27 {
    //往后遍历，记录当前等于val的元素数量，遇到不等于的元素将其往前移动当前记录数量的步数即可
    public int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==val){
                count++;
            }else{
                nums[i-count] = nums[i];
            }
        }
        return nums.length-count;
    }

    //双指针交换
    public int removeElement2(int[] nums, int val) {
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                //i--巧妙地检查了交换之后的值是否仍然为val
                swap(nums, i--, j--);
            }
        }
        return j + 1;
    }
    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    //与做法一类似，但是直接记录的是不等于val的元素当前要移动的坐标
    public int removeElement3(int[] nums, int val) {
        int idx = 0;
        for (int x : nums) {
            if (x != val) nums[idx++] = x;
        }
        return idx;
    }


}
