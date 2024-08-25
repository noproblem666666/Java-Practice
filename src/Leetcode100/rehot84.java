package Leetcode100;

import java.util.LinkedList;

//字符串解码（Todo：主要考虑括号嵌套的问题）
//递归和辅助栈都可以
public class rehot84 {
    //无法解决括号嵌套的问题
    public String decodeString(String s){
        char[] bytes = s.toCharArray();
        StringBuilder res = new StringBuilder();
        int n = 0;
        while(n<bytes.length){
            if(bytes[n] >= '1' && bytes[n] <= '9'){
                int left = n+1;
                while(bytes[left]!='['){
                    left++;
                }
                //Todo：寻找右括号不能找第一个，可能有嵌套
                int right= left+1;
                while(bytes[right]!=']'){
                    right++;
                }
                //substring包左不包右
                String times = s.substring(n,left);
                //Todo：即使找对了括号，截出的字符串中仍然可能有下一层的括号
                String str = s.substring(left+1,right);
                int time = Integer.parseInt(times);
                while(time>0){
                    res.append(str);
                    time--;
                }
                //最后更新下标
                n = right;
            }else{
                res.append(bytes[n]);
            }
            n++;
        }
        return res.toString();
    }


    public String decodeString2(String s){
        return decode(s);
    }
    public String decode(String s){
        char[] bytes = s.toCharArray();
        StringBuilder res = new StringBuilder();
        int n = 0;
        while(n<bytes.length){
            if(bytes[n] >= '1' && bytes[n] <= '9'){
                int left = n+1;
                while(bytes[left]!='['){
                    left++;
                }
                int right= left+1;
                //需要找到对应一层的右括号,可能会有多个括号嵌套，所以用sum来计数
                int sum = 0;
                while(sum > 0 || bytes[right]!=']'){
                    if(bytes[right]=='['){
                        sum++;
                    }
                    if(bytes[right]==']'){
                        sum--;
                    }
                    right++;
                }
                //substring包左不包右
                String times = s.substring(n,left);
                String str = s.substring(left+1,right);
                str = decode(str);
                int time = Integer.parseInt(times);
                while(time>0){
                    res.append(str);
                    time--;
                }
                //最后更新下标
                n = right;
            }else{
                res.append(bytes[n]);
            }
            n++;
        }
        return res.toString();
    }

    //辅助栈法
    public String decodeString3(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        //存储数字的栈
        LinkedList<Integer> stack_multi = new LinkedList<>();
        //存储字符串的栈
        LinkedList<String> stack_res = new LinkedList<>();
        for(Character c : s.toCharArray()) {
            if(c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            }
            else if(c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for(int i = 0; i < cur_multi; i++) tmp.append(res);
                //栈中取出最后一层的字符串进行拼接
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }
            else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }
        return res.toString();
    }

}
