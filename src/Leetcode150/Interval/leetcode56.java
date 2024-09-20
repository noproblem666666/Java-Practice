package Leetcode150.Interval;

import java.util.Arrays;
import java.util.Comparator;

//Todo：合并区间
public class leetcode56 {
    //一直与后面的比较
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[][] res = new int[intervals.length][2];
        int index = 1;
        res[0] = intervals[0];
        for (int i = 0; i < intervals.length; ) {
            //注意这里防止越界，并且比较条件是>=,防止边界重合的条件下没有合并
            while (i < intervals.length && res[index - 1][1] >= intervals[i][0]) {
                res[index - 1][1] = Math.max(res[index - 1][1], intervals[i][1]);
                i++;
            }
            //这里也要防止数组越界
            if(i== intervals.length){
                break;
            }
            res[index] = intervals[i];
            i++;
            index++;
        }
        return Arrays.copyOf(res, index);
    }


    //不使用栈或者动态数组，直接在数组上进行合并（一直与前面的比较，更加简单）
    public int[][] merge2(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}
