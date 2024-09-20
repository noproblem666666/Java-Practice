package Leetcode150.StringAndList;

import java.util.Arrays;
import java.util.Stack;

//接雨水
public class leetcode42 {
    //对每列分别计算能接多少雨水，两次遍历记下每个位置的左右最高边
    //也可对每行分别计算能接多少雨水
    //用单调栈一次遍历计算

    //按列计算
    public int trap(int[] height) {
        int sum = 0;
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = 0;
        int max_height = 0;
        //更新每一列左边的最高边
        for(int i = 1;i<len;i++){
            max_height = Math.max(max_height,height[i-1]);
            left[i] = max_height;
        }
        right[len-1] = 0;
        //最高值重置为0
        max_height = 0;
        //更新每一列右边的最高边
        for(int i = len-2;i>=0;i--){
            max_height = Math.max(max_height,height[i+1]);
            right[i] = max_height;
        }
        for(int i = 0;i<len;i++){
            //木桶效应
            int edge = Math.min(left[i],right[i]);
            sum += Math.max(0,edge-height[i]);
        }
        return sum;
    }

    //双指针优化空间，通过比较左右两边目前的最大高度，得知应该先计算哪一边的水量，然后移动指针
    public int trap2(int[] height) {
        int n = height.length;
        int res = 0;
        // 左右指针：分别指向左右两边界的列
        int left = 0, right = n - 1;
        // 左指针的左边最大高度、右指针的右边最大高度
        int leftMax = height[left], rightMax = height[right];
        // 最两边的列存不了水
        left++;
        right--;
        // 向中间靠拢
        while(left <= right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if(leftMax < rightMax){
                // 左指针的leftMax比右指针的rightMax矮
                // 说明：左指针的右边至少有一个板子 > 左指针左边所有板子
                // 根据水桶效应，保证了左指针当前列的水量决定权在左边
                // 那么可以计算左指针当前列的水量：左边最大高度-当前列高度
                res += leftMax - height[left];
                left++;
            }else{
                // 右边同理
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }

    //按行求  如果求高度为 i 的水，首先用一个变量 temp 保存当前累积的水，初始化为 0。
    //从左到右遍历墙的高度，遇到高度大于等于 i 的时候，开始更新 temp。
    // 更新原则是遇到高度小于 i 的就把 temp 加 1，遇到高度大于等于 i 的，
    // 就把 temp 加到最终的答案 ans 里，并且 temp 置零，然后继续循环。

    public int trap3(int[] height) {
        int sum = 0;
        int max = getMax(height);//找到最大的高度，以便遍历。
        for (int i = 1; i <= max; i++) {
            boolean isStart = false; //标记是否开始更新 temp
            int temp_sum = 0;
            for (int j = 0; j < height.length; j++) {
                if (isStart && height[j] < i) {
                    temp_sum++;
                }
                if (height[j] >= i) {
                    sum = sum + temp_sum;
                    temp_sum = 0;
                    isStart = true;
                }
            }
        }
        return sum;
    }
    private int getMax(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        return max;
    }

    //单调栈
    public int trap4(int[] height) {
        int sum = 0;
        //由于高度可以数组随机访问查询，栈中存放下标更方便
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            //                                       == 的时候还不用移除，因为此时没有高度差，肯定没有积水
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                //由算法中单调栈的性质，此处min-h必定大于等于0
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }

}
