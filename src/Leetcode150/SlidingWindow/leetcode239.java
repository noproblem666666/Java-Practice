package Leetcode150.SlidingWindow;
//滑动窗口最大值

import java.util.*;

//单调队列
public class leetcode239 {

    //使用优先队列
    public int[] maxSlidingWindow(int[] nums, int k) {
        //优先队列存储数字和对应下标，队列自动对数字大小进行排序，最大值排在堆顶
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            priorityQueue.add(new int[]{nums[i], i});
        }
        res[0] = priorityQueue.peek()[0];
        for (int i = k; i < nums.length; i++) {
            priorityQueue.add(new int[]{nums[i], i});
            //每次获取最大值时检查其下标是否超出窗口
            while (priorityQueue.peek()[1] < i - k + 1) {
                priorityQueue.poll();
            }
            res[i-k+1] = priorityQueue.peek()[0];
        }
        return res;
    }

    //单调队列（双端队列）
    public int[] maxSlidingWindow2(int[] nums, int k) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{nums[0],0});
        for(int i = 1;i<k;i++){
            while(!deque.isEmpty()&&deque.getLast()[0]<=nums[i]){
                deque.removeLast();
            }
            deque.addLast(new int[]{nums[i],i});
        }
        int len = nums.length-k+1;
        int[] res = new int[len];
        res[0] = deque.getFirst()[0];
        for(int i = k;i< nums.length;i++){
            while(!deque.isEmpty()&&deque.getLast()[0]<=nums[i]){
                deque.removeLast();
            }
            deque.addLast(new int[]{nums[i],i});
            while(deque.getFirst()[1]<i-k+1){
                deque.removeFirst();
            }
            res[i-k+1] = deque.getFirst()[0];
        }
        return res;
    }
}

