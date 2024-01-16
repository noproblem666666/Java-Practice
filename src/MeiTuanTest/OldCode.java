package MeiTuanTest;

import java.util.ArrayList;
import java.util.Scanner;

public class OldCode {
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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            if(scanner.nextInt()==1){
                break;
            }
        }

        String[] test = new String[5];
        test[0] = "25525511135";
        test[1] = "0000";
        test[2] = "101023";
        test[3] = "000256";
        test[4] = "25514511139";
        OldCode oldCode = new OldCode();

        long start = 0;
        long end = 0;

        System.out.println("之前测试时用的旧代码:");
        start = System.currentTimeMillis();
        for (int i = 0;i<100;i++){
            for (String s : test) {
                oldCode.restoreIpAddresses(s);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start)+"ms");
        while(true){

        }
    }
}
