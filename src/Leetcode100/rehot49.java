package Leetcode100;

import java.util.*;

//单词拆分(动态规划)
public class rehot49 {
    //递归回溯加字符串拆分，超时
    //Todo：需要做记忆化
    boolean isBreak = false;
    public boolean wordBreak(String s, List<String> wordDict) {
        find(s,-1,wordDict);
        return isBreak;
    }
    public void find(String s,int k,List<String> wordDict){
        if(k==s.length()-1){
            isBreak = true;
        }
        for (int i = 0; i < wordDict.size(); i++) {
            String temp = wordDict.get(i);
            if(k+temp.length()<=s.length()-1){
                //substring包左不包右
                if(temp.equals(s.substring(k+1,k+temp.length()+1))){
                    find(s,k+temp.length(),wordDict);
                }
            }
        }
    }

    //动态规划
    public boolean wordBreak2(String s, List<String> wordDict) {
        //以i为结尾的字符能否拼接到达
        //为了方便判断开头条件，也可以数组变长一个值
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < wordDict.size(); j++) {
                String temp = wordDict.get(j);
                int len = temp.length();
                //注意这个条件判断式，只要不是开头第一个，剩下的要改为true，不仅自身要匹配，还必须前面相连的那个状态为true
                if (i - len + 1 >= 0 && (i-len==-1|| dp[i - len]) && s.substring(i - len + 1, i + 1).equals(temp)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[s.length()-1];
    }


    //答案写的更加简洁
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


}
