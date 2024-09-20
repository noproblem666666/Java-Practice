package Leetcode150.StringAndList;

import java.util.HashMap;

public class leetcode13 {
    //需要交换的数全放在hashmap中直接查询相加即可
    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int sum = 0;
        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("I",1);
        hashMap.put("IV",4);
        hashMap.put("V",5);
        hashMap.put("IX",9);
        hashMap.put("X",10);
        hashMap.put("XL",40);
        hashMap.put("L",50);
        hashMap.put("XC",90);
        hashMap.put("C",100);
        hashMap.put("CD",400);
        hashMap.put("D",500);
        hashMap.put("CM",900);
        hashMap.put("M",1000);

        for(int i = 0;i<n;i++){
            if(i == n-1){
                sum+= hashMap.get(s.substring(n-1));
                break;
            }
            String twoChars = s.substring(i,i+2);
            String oneChar = s.substring(i,i+1);
            if(hashMap.containsKey(twoChars)){
                sum+=hashMap.get(twoChars);
                i++;
                continue;
            }
            sum+=hashMap.get(oneChar);
        }
        return sum;
    }

    //多看一样后一位，决定做加法还是减法
    public int romanToInt2(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            //当前位置的元素比下个位置的元素小，就减去当前值，否则加上当前值
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
