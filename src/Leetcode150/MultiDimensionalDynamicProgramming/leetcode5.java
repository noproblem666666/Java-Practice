package Leetcode150.MultiDimensionalDynamicProgramming;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.Callable;

//最长回文子串（多维动态规划）
public class leetcode5 extends Thread{
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        //记录当前最长回文子串和它的左右边界
        int maxlen = 1;
        int left = 0;
        int right = 0;
        //表示从i到j是否是一个回文串
        boolean[][] dp = new boolean[len][len];
        char[] chars = s.toCharArray();
        for (int k = 1; k < len; k++) {
            for (int index = 0; index + k < len; index++) {
                //注意这里的条件设置，别遗漏了对区间大小的判断
                if (chars[index] == chars[index + k] && (k <= 2 || dp[index + 1][index + k - 1])) {
                    dp[index][index+k] = true;
                    //k是步长，k+1才是区间长度
                    if(k+1>maxlen){
                        maxlen = k+1;
                        left = index;
                        right = index+k;
                    }
                }
            }
        }
        //substring()函数包左不包右
        return s.substring(left,right+1);
    }

    //中心扩散法（分为奇数长度和偶数长度两种情况）
    public String longestPalindrome2(String s) {
        int len = s.length();
        if(len < 2) return s;

        int maxLen = 0;
        // 数组第一位记录起始位置，第二位记录长度
        int[] res = new int[2];
        for (int i = 0; i < s.length() - 1; i++) {
            int[] odd = centerSpread(s, i, i);
            int[] even = centerSpread(s, i, i + 1);
            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1] > maxLen) {
                res = max;
                maxLen = max[1];
            }
        }
        return s.substring(res[0], res[0] + res[1]);
    }

    private int[] centerSpread(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left + 1, right - left - 1};
    }

}
