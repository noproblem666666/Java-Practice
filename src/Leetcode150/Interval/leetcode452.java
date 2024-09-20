package Leetcode150.Interval;

import java.util.*;

//用最少数量的箭引爆气球（与合并区间不同，这里其实只需要维护当前的最小右边界即可）（排序+贪心算法）
public class leetcode452 {
    public int findMinArrowShots(int[][] points) {
        if(points.length==0){
            return 0;
        }
        //对原始数组按左边界升序排序，便于一次遍历处理
        //Todo:v1[0]-v2[0]可能会产生溢出，导致排序结果错误，用下面的Integer.compare()方法可以避免
        //Arrays.sort(points,(v1,v2)->v1[0]-v2[0]);
        Arrays.sort(points,(v1,v2)->Integer.compare(v1[0],v2[0]));
        int min_right = points[0][1];
        int count = 1;
        for(int i = 1;i< points.length;i++){
            if(points[i][0]<=min_right){
                min_right = Math.min(min_right,points[i][1]);
            }else{
                min_right = points[i][1];
                count++;
            }
        }
        return count;
    }

    //另一种解法
    public int findMinArrowShots2(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon: points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }

}
