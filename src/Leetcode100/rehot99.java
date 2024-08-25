package Leetcode100;

//回文子串（状态转移方程或中心扩展法）
public class rehot99 {
    public int countSubstrings(String s) {
        //表示从i到j的字符串是否是回文
        boolean[][] dp = new boolean[s.length()][s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for(int k=1;k<s.length();k++){
            for (int i = 0; i < s.length() - k; i++) {
                boolean b = chars[i] == chars[i + k];
                if(k==1){
                    dp[i][i+k] = b;
                    continue;
                }
                //首尾必须相同，其内部也必须是回文
                dp[i][i+k] = b && dp[i+1][i+k-1];
            }
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for(int j = i;j<s.length();j++){
                if(dp[i][j]){
                    count++;
                }
            }
        }
        return count;
    }

    //中心扩展法（有单数中心和双数中心）
    public int countSubstrings2(String s) {
        // 中心扩展法
        int ans = 0;
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            // left和right指针和中心点的关系是？
            // 首先是left，有一个很明显的2倍关系的存在，其次是right，可能和left指向同一个（偶数时），也可能往后移动一个（奇数）
            // 大致的关系出来了，可以选择带两个特殊例子进去看看是否满足。
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

}
