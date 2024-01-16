package MeiTuanTest;

import java.util.*;

//使用环境为JDK17
public class TimeTest {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param s string字符串
     * @return string字符串ArrayList
     */
    //这是我测试时候的旧代码，主要使用了递归回溯和StringBuffer字符串拼接的方法进行字符串的转化
    //Todo:尝试新的方法，处理好边界值，不使用StringBuffer类
    public ArrayList<String> restoreIpAddresses(String s) {
        // write code here
        ArrayList<String> res = new ArrayList<>();
        restore(s, 0, 0, res, new StringBuffer());
        return res;
    }
    public void restore(String s, int step, int index, ArrayList<String> list, StringBuffer string) {
//        System.out.println(step + " " + index);
//        System.out.println(string.toString());
        if (step == 4) {
            if (index == s.length()) {
                list.add(string.toString());
                return;
            } else {
                return;
            }
        }
        if (index == s.length()) {
            return;
        }
        int i = 1;
        while (i <= 3 && index + i <= s.length()) {

            String temp = s.substring(index, index + i);
            //System.out.println(temp);
            if (temp.charAt(0) == '0' && temp.length() > 1) {
                return;
            }
            if (temp.equals("00")) {
                return;
            }
            int sum = Integer.parseInt(temp);
            if (sum > 255) {
                i++;
                continue;
            }
            string.append(temp);
            if (step != 3) {
                string.append(".");
            }
            restore(s, step + 1, index + i, list, string);
//            System.out.println(i);
            if (step != 3) {
                string.deleteCharAt(string.length() - 1);
            }

            int count = i;
            while (count > 0) {
                string.deleteCharAt(string.length() - 1);
                count--;
            }
            i++;
        }
    }

    //这是我改进的新的代码，这次不使用StringBuffer进行拼接，使用栈完成IP字段的存储，清除和最后的拼接
    public ArrayList<String> restoreIpAddresses2(String s) {
        //先对s字符串进行一个判断

        ArrayList<String> res = new ArrayList<>();
        DFS(s,0,0,new ArrayDeque<>(),res);
        return res;
    }

    public void DFS(String s, int step , int index, Deque<String> deque, List<String> res){
        //System.out.println(step + " "+index);
        if(step == 4){
            if(index == s.length()){
                //这个方法底层使用的是StringBuilder
                res.add(String.join(".",deque));
                return;
            }else{
                return;
            }
        }
        if(index ==s.length()){
            return;
        }
        int i = 1;
        while(i<=3 && index+i<=s.length()){
            //以0开头的字段只能有一位0，后面接其他数字都直接返回
            if(i>1&&s.charAt(index)=='0'){
                return;
            }
            int sum = 0;
            int tempIndex = index;
            while(tempIndex<index+i){
                sum = sum*10+s.charAt(tempIndex)-'0';
                tempIndex++;
            }
            if(sum>=0&&sum<=255){
                //判断数字合理，加入栈
                deque.addLast(s.substring(index,index+i));
            }else{
                i++;
                continue;
            }
            DFS(s,step+1,index+i,deque,res);
            //递归后去掉本次递归加入的数字
            deque.removeLast();
            i++;
        }
    }

    //暴力分段的方法，时间复杂度非常差，用来做对比测试
    public ArrayList<String> restoreIpAddresses3 (String s) {
        ArrayList<String> res = new ArrayList<String>();
        int n = s.length();
        //遍历IP的点可能的位置（第一个点）
        for(int i = 1; i < 4 && i < n - 2; i++){
            //第二个点的位置
            for(int j = i + 1; j < i + 4 && j < n - 1; j++){
                //第三个点的位置
                for(int k = j + 1; k < j + 4 && k < n; k++){
                    //最后一段剩余数字不能超过3
                    if(n - k >= 4)
                        continue;
                    //从点的位置分段截取
                    String a = s.substring(0, i);
                    String b = s.substring(i, j);
                    String c = s.substring(j, k);
                    String d = s.substring(k);
                    //IP每个数字不大于255
                    if(Integer.parseInt(a) > 255 || Integer.parseInt(b) > 255 || Integer.parseInt(c) > 255 || Integer.parseInt(d) > 255)
                        continue;
                    //排除前导0的情况
                    if((a.length() != 1 && a.charAt(0) == '0') || (b.length() != 1 && b.charAt(0) == '0') ||  (c.length() != 1 && c.charAt(0) == '0') || (d.length() != 1 && d.charAt(0) == '0'))
                        continue;
                    //组装IP地址
                    String temp = a + "." + b + "." + c + "." + d;
                    res.add(temp);
                }
            }
        }
        return res;
    }

    //递归但是使用string字符串直接进行拼接,时间复杂度很差，用来进行测试比较
    private String nums = "";
    //step表示第几个数字，index表示字符串下标
    public void dfs(String s, ArrayList<String> res, int step, int index){
        //当前分割出的字符串
        String cur = "";
        //分割出了四个数字
        if(step == 4){
            //下标必须走到末尾
            if(index != s.length())
                return;
            res.add(nums);
        }else{
            //最长遍历3位
            for(int i = index; i < index + 3 && i < s.length(); i++){
                cur += s.charAt(i);
                //转数字比较
                int num = Integer.parseInt(cur);
                String temp = nums;
                //不能超过255且不能有前导0
                if(num <= 255 && (cur.length() == 1 || cur.charAt(0) != '0')){
                    //添加点
                    if(step - 3 != 0)
                        nums += cur + ".";
                    else
                        nums += cur;
                    //递归查找下一个数字
                    dfs(s, res, step + 1, i + 1);
                    //回溯
                    nums = temp;
                }
            }
        }
    }
    public ArrayList<String> restoreIpAddresses4 (String s) {
        ArrayList<String> res = new ArrayList<String>();
        dfs(s, res, 0, 0);
        return res;
    }

    public static void main(String[] args) {
        String[] test = new String[5];
        test[0] = "25525511135";
        test[1] = "0000";
        test[2] = "101023";
        test[3] = "000256";
        test[4] = "25514511139";
        TimeTest stringToIP = new TimeTest();

        long start = 0;
        long end = 0;

        System.out.println("之前测试时用的旧代码:");
        start = System.currentTimeMillis();
        for (int i = 0;i<100;i++){
            for (String s : test) {
                stringToIP.restoreIpAddresses(s);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start)+"ms");

        System.out.println("更新的代码:");
        start = System.currentTimeMillis();
        for (int i = 0;i<100;i++){
            for (String s : test) {
                stringToIP.restoreIpAddresses2(s);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start)+"ms");

        System.out.println("暴力分段代码:");
        start = System.currentTimeMillis();
        for (int i = 0;i<100;i++){
            for (String s : test) {
                stringToIP.restoreIpAddresses3(s);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start)+"ms");

        System.out.println("使用string拼接的的代码:");
        start = System.currentTimeMillis();
        for (int i = 0;i<100;i++){
            for (String s : test) {
                stringToIP.restoreIpAddresses4(s);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start)+"ms");

    }

}


