package Leetcode150.DoublePoint;
//两数之和 II - 输入有序数组
public class leetcode167 {
    //双指针向中间靠拢
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int left = 0;
        int right = n-1;
        while(left<right){
            int sum = numbers[left] + numbers[right];
            if(sum == target){
                return new int[]{left+1,right+1};
            }else if(sum<target){
                left++;
            }else{
                right--;
            }
        }
        return new int[]{0,0};
    }
}
