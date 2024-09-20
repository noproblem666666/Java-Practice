package Leetcode150.BinarySearch;
//搜索插入位置
public class leetcode35 {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len-1;
        while(left<=right){
            int mid = (right+left)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        //Todo:最后直接返回left即可
        return left;
    }
}
