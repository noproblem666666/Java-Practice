package Leetcode150.Stack;

import java.util.HashMap;
import java.util.Stack;

//有效的括号
public class leetcode20 {
    public boolean isValid(String s) {
        int len = s.length();
        if(len%2==1){
            return false;
        }
        //便于查找另一半的括号
        HashMap<Character,Character> hashMap = new HashMap<>();
        hashMap.put(')','(');
        hashMap.put(']','[');
        hashMap.put('}','{');
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for(int i = 0;i<len;i++){
            if(chars[i]=='('||chars[i]=='['||chars[i]=='{'){
                stack.push(chars[i]);
            }else{
                if(stack.isEmpty()||stack.peek()!=hashMap.get(chars[i])){
                    return false;
                }
                stack.pop();
            }
        }
        //只有所有括号都匹配才算成功
        return stack.isEmpty();
    }
}
