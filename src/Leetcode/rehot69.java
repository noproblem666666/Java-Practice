package Leetcode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

//滑动窗口最大值 Todo：优先队列和双端单调队列
public class rehot69 {
    //优先队列
    public int[] maxSlidingWindow(int[] nums, int k) {
        //因为还要判断这个数是否在滑动窗口中，只存储数不够，还需要用数组存储下标
        //默认是小顶堆，初始化均按大顶堆来
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
        }
        int len = nums.length - k + 1;
        int[] res = new int[len];
        res[0] = priorityQueue.peek()[0];
        for (int i = k; i < nums.length; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
            while (priorityQueue.peek()[1] < i - k + 1) {
                priorityQueue.poll();
            }
            res[i - k + 1] = priorityQueue.peek()[0];
        }
        return res;
    }

    //由于我们需要求出的是滑动窗口的最大值，如果当前的滑动窗口中有两个下标 i 和 j，其中 i 在 j 的左侧（i<j），
    // 并且 i 对应的元素不大于 j 对应的元素（nums[i]≤nums[j]），那么会发生什么呢？
    //当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中，这是 i 在 j 的左侧所保证的。
    // 因此，由于 nums[j] 的存在，nums[i] 一定不会是滑动窗口中的最大值了，我们可以将 nums[i]永久地移除。

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

    //分块+预处理
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            }
            else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }



}
