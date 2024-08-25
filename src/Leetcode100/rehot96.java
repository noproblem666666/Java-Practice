package Leetcode100;

import java.util.Stack;

//最短无序连续子数组（要求时间复杂度为o（n）否则可以用排序之后找不同）
public class rehot96 {
    //一次遍历不是o（n）时间复杂度，会超时
    Stack<int[]> stack = new Stack<>();
    Stack<int[]> temp = new Stack<>();
    public int findUnsortedSubarray(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int left = -1;
        int right = -1;
        stack.push(new int[]{0,nums[0]});
        for (int i=1;i<nums.length;i++) {
            if(nums[i]>=stack.peek()[1]){
                stack.push(new int[]{i,nums[i]});
                continue;
            }
            right = i + 1;
            while(!stack.isEmpty()&&stack.peek()[1]>nums[i]){
                temp.push(stack.pop());
            }
            if(stack.isEmpty()){
                left = 0;
            }else{
                if(left==-1){
                    left = stack.peek()[0] + 1;
                }else{
                    left = Math.min(left,stack.peek()[0] + 1);
                }
            }
            stack.push(new int[]{i,nums[i]});
            while(!temp.isEmpty()){
                stack.push(temp.pop());
            }
        }
        System.out.println(left);
        System.out.println(right);
        return right-left;
    }

    //两次遍历（其实通过下标操作合为了一次），从左到右维持最大值，寻找右边界end，从右到左维持最小值，寻找左边界begin
    public int findUnsortedSubarray2(int[] nums) {
        //初始化
        int len = nums.length;
        int min = nums[len-1];
        int max = nums[0];
        int begin = 0, end = -1;
        //遍历
        for(int i = 0; i < len; i++){
            if(nums[i] < max){      //从左到右维持最大值，寻找右边界end
                end = i;
            }else{
                max = nums[i];
            }

            if(nums[len-i-1] > min){    //从右到左维持最小值，寻找左边界begin
                begin = len-i-1;
            }else{
                min = nums[len-i-1];
            }
        }
        return end-begin+1;
    }

    public static void main(String[] args) {
        rehot96 s = new rehot96();
        int[] nums = new int[]{1,3,5,4,2};
        System.out.println(s.findUnsortedSubarray(nums));
    }
}
