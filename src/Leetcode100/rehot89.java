package Leetcode100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//找到字符串中的所有字母异位词（滑动窗口算法）
public class rehot89 {

    public List<Integer> findAnagrams(String s, String p) {
        int sum = p.length();
        //记录p中的字母和出现次数
        Map<Character, Integer> p_map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        char[] p_chars = p.toCharArray();
        for (char p_char : p_chars) {
            p_map.put(p_char, p_map.getOrDefault(p_char, 0) + 1);
        }
        char[] s_chars = s.toCharArray();
        //记录窗口中的字母和出现次数
        Map<Character, Integer> temp = new HashMap<>();
        int count = 0;
        int left = 0;
        for (int i = 0; i < s_chars.length; i++) {
            //需要的字母放入窗口中
            if (p_map.containsKey(s_chars[i]) && temp.getOrDefault(s_chars[i], 0) < p_map.get(s_chars[i])) {
                count++;
                temp.put(s_chars[i], temp.getOrDefault(s_chars[i], 0) + 1);
            } else if (!p_map.containsKey(s_chars[i])) { //有不包含的字母，直接放弃掉所有窗口中的字母
                left = i + 1;
                temp.clear();
                count=0;
            } else { //字母出现次数超了，左边界一直右移到去掉相同的字母
                while (s_chars[left] != s_chars[i]) {
                    temp.put(s_chars[left], temp.get(s_chars[left]) - 1);
                    left++;
                    count--;
                }
                //最后这一步只需要左移边界，右边界在循环中控制，相同的字母一加一减等于没有变
                left++;
            }
            //判断窗口是否满足要求并对左边界进行右移
            if(count==sum){
                result.add(left);
                temp.put(s_chars[left], temp.get(s_chars[left]) - 1);
                left++;
                count--;
            }
        }
        return result;
    }
}
