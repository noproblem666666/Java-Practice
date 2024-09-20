package Leetcode150.HashMap;

import java.util.*;

//字母异位词分组(字符串中只包含小写字母)，想办法拼接成字符串比较，而不是比较哈希表和数组，这样比较难度较高
public class leetcode49 {
    //排序
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> hashMap = new HashMap<>();
        for(String s : strs){
            char[] temp = s.toCharArray();
            //将异位词重排序后肯定相同
            Arrays.sort(temp);
            //Todo：不能直接用temp.toString,返回的不是可读的字符串表示，应该用Arrays.toString()
            //Todo:也可使用String key = new String(array)转换成字符串，这样转换没有数组中的括号和逗号，直接拼接
            String n = Arrays.toString(temp);
            if(!hashMap.containsKey(n)){
                hashMap.put(n,new ArrayList<>());
            }
            hashMap.get(n).add(s);
        }
        System.out.println(hashMap.toString());
        //将哈希表的值转化为链表进行返回

        return new ArrayList<>(hashMap.values());
    }

    //计数
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

}
