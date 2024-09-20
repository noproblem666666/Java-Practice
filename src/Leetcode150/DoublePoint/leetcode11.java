package Leetcode150.DoublePoint;
//盛最多水的容器
public class leetcode11 {
    //决定两条线之间水容量的是两线之间的距离以及两线之中的较短值
    //双指针法
    //若向内 移动短板 ，水槽的短板 min(h[i],h[j]) 可能变大，因此下个水槽的面积可能增大 。
    //若向内 移动长板 ，水槽的短板 min(h[i],h[j]) 不变或变小，因此下个水槽的面积一定变小 。
    public int maxArea(int[] height) {
        int slow = 0;
        int fast = height.length-1;
        int max = 0;
        while(slow<fast){
            int count = (fast-slow)*Math.min(height[slow],height[fast]);
            max = Math.max(max , count);
            //因为最大容量必然在双指针之间产生，贪心算法，每次放弃较短的边才有可能增大容积（否则距离变短，高度也不会增长）
            if(height[slow]<height[fast]){
                slow++;
            }else{
                fast--;
            }
        }
        return max;
    }


}
