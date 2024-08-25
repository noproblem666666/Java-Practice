package Leetcode100;

import java.util.Stack;

//每日温度
public class rehot100 {
    //栈存储下标更方便，数值可以用数组查
    Stack<Integer> stack = new Stack<>();
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        if(temperatures.length==1){
            return new int[]{0};
        }
        stack.push(0);
        for(int i = 1;i<temperatures.length;i++){
            while(!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                res[stack.peek()] = i-stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        //数组默认值是0.不用检查栈中剩余元素
        return res;
    }
}
