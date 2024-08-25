package Leetcode150.StringAndList;

// 除自身以外数组的乘积
public class leetcode238 {
    //动态规划，从左到右和从右到左两次遍历
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        //分别表示i左右两侧的乘积列表
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            left[i + 1] = left[i] * nums[i];
        }
        for (int i = n - 1; i > 0; i--) {
            right[i - 1] = right[i] * nums[i];
        }
        int[] res = new int[n];
        for (int i = 0; i < res.length; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }

    //在原数组上计算，不设左右数组，只用输出数组
    public int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];

        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }
        return answer;
    }

}
