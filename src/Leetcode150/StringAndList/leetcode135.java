package Leetcode150.StringAndList;

import java.util.Arrays;

// 分发糖果（贪心算法）
public class leetcode135 {
    //两次循环，确保满足左右规则
    public int candy(int[] ratings) {
        //存放糖果数量
        int[] candy = new int[ratings.length];
        Arrays.fill(candy, 1);

        for (int i = 1; i < ratings.length; i++) {
            //左规则循环
            if (ratings[i - 1] < ratings[i]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        int count = candy[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            //右规则循环，如果左边的评分比右边的大且当前数组中值不比右边的大，则修改值
            if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) {
                candy[i] = candy[i + 1] + 1;
            }
            count += candy[i];
        }
        return count;
    }
}
