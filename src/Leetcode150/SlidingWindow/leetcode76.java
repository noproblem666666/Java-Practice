package Leetcode150.SlidingWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


//最小覆盖子串（滑动窗口算法）
public class leetcode76 {
    //用哈希表计数（注意哈希表中get到的是Integer包装类，不能使用==号直接比较，否则比较的是地址,要么用equals,要么手动拆箱）
    public String minWindow(String s, String t) {
        //记录t中所有出现的字符和次数
        HashMap<Character, Integer> tChars = new HashMap<>();
        //记录当前窗口中出现的字符和次数
        HashMap<Character, Integer> window = new HashMap<>();
        char[] t_chars = t.toCharArray();
        char[] s_chars = s.toCharArray();
        for (char t_char : t_chars) {
            tChars.put(t_char, tChars.getOrDefault(t_char, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int count = 0;
        int len = s_chars.length;
        String minString = "";
        int min = Integer.MAX_VALUE;
        while (right < len) {
            char r_temp = s_chars[right];
            window.put(r_temp, window.getOrDefault(r_temp, 0) + 1);
            if (tChars.containsKey(r_temp) && window.get(r_temp) <= tChars.get(r_temp)) {
                count++;
            }
            while (left <= right && count == t_chars.length) {
                char l_temp = s_chars[left];
                //无论是否达到了左窗口的边界，都要计算一次最小值，防止遗漏情况
                if(right-left+1<min){
                    min = right-left+1;
                    minString = s.substring(left,right+1);
                }
                //Todo:比较的时候一定要用equals或者window.get(r).intValue() == need.get(r).intValue()，因为会使用到Integer的cache
                //因为直接==比较的是Integer类的地址，是引用对象而不是int
                //如果这里是>=反而不会错
                if (tChars.containsKey(l_temp) && Objects.equals(tChars.get(l_temp), window.get(l_temp))) {
                    count--;
                }
                left++;
                window.put(l_temp, window.get(l_temp) - 1);
            }
            //不要忘了while循环里最后的变值
            right++;
        }
        return minString;
    }

    //更加优化的答案
    public String minWindow2(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty() || s.length() < t.length()) return "";

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // 初始化 need，记录 t 中每个字符的出现次数
        for (char c : t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

        int left = 0, right = 0; // 窗口的左右边界
        int valid = 0; // 已经匹配上的字符数量
        int start = 0, minLen = Integer.MAX_VALUE; // 最小窗口的起始位置和长度

        while (right < s.length()) {
            char r = s.charAt(right);
            right++;

            // 更新窗口内字符的计数
            if (need.containsKey(r)) {
                window.put(r, window.getOrDefault(r, 0) + 1);
                if (window.get(r).equals(need.get(r))) valid++;
            }

            // 当窗口内的字符已经完全包含了 t 中的所有字符时
            while (valid == need.size()) {
                // 更新最小窗口的起始位置和长度
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }

                char l = s.charAt(left);
                // 缩小窗口，移动左边界
                if (need.containsKey(l)) {
                    window.put(l, window.get(l) - 1);
                    if (window.get(l) < need.get(l)) valid--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

}
