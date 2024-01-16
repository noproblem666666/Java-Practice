package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Stack;

//最小栈
public class rehot55 {
    //一个栈正常存储，一个单调栈（等于也要加入，防止最小栈先排空）
    class MinStack {
        Deque<Integer> deque;
        Deque<Integer> minDeque;

        MinStack() {
            deque = new ArrayDeque<>();
            minDeque = new ArrayDeque<>();
        }

        void push(int val) {
            deque.push(val);
            if (minDeque.isEmpty() || minDeque.peek() >= val) {
                minDeque.push(val);
            }
        }

        void pop() {
            //Integer包装类不能用==比较，如minDeque.peek()==deque.pop(),不然比较的是地址
            //也可以先读出来隐式转化为int
//            int temp = deque.pop();
//            if(temp == minDeque.peek()){
//                minDeque.pop();
//            }
            if (Objects.equals(minDeque.peek(), deque.pop())) {
                minDeque.pop();
            }
        }

        int top() {
            return deque.peek();
        }

        int getMin() {
            return minDeque.peek();
        }
    }

    //只用一个栈，但是栈中同时存放当前值和当前值对应的最小值
    class MinStack2 {

        // 数组栈, [当前值, 当前最小值]
        private Stack<int[]> stack = new Stack<>();
        public MinStack2() {

        }

        public void push(int x) {
            if (stack.isEmpty()){
                stack.push(new int[]{x, x});
            }else {
                stack.push(new int[]{x, Math.min(x, stack.peek()[1])});
            }
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }
}
