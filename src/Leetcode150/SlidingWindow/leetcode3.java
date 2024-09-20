package Leetcode150.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//无重复字符的最长子串
public class leetcode3 {
    //滑动窗口
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if(len == 0){
            return 0;
        }
        int max_len = 0;
        int left = 0;
        //right指针可以直接用循环中的i代替，计算长度时加一
        int right = 0;
        //用set判断是否有重复字符
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        for(int i = 0;i<len;i++){
            if(!set.contains(chars[i])){
                set.add(chars[i]);
            }else{
                while(chars[left] != chars[i]){
                    //移除窗口中的元素
                    set.remove(chars[left]);
                    left++;
                }
                left++;
            }
            right++;
            max_len = Math.max(max_len,right-left);
        }
        return max_len;
    }

    //更加简洁的写法
    public int lengthOfLongestSubstring2(String s) {
        if (s.length()==0) return 0;
        //存储窗口中上一次出现该元素的下标
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                //如果被判断的字符上一次出现的位置就在滑动窗口内，则需要left改变位置，改变为该字符上次出现位置+1
                //最大值比较函数保证了即使不删除窗口外的字符，右指针碰到该元素也不会让左指针后退
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            //不管字符是否出现在窗口中，都要将字符的下标更新
            map.put(s.charAt(i),i);
            //用i代替右指针计算窗口长度需要加一
            max = Math.max(max,i-left+1);
        }
        return max;
    }

}
