package MeiTuanTest;

import java.util.ArrayList;
import java.util.Scanner;

public class Test1CodeEnum {
    //暴力分段的方法，时间复杂度非常差，用来做对比测试
    public ArrayList<String> restoreIpAddresses (String s) {
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

        Test1CodeEnum test1CodeEnum = new Test1CodeEnum();

        long start = 0;
        long end = 0;

        System.out.println("暴力分段代码:");
        start = System.currentTimeMillis();
        for (int i = 0;i<100;i++){
            for (String s : test) {
                test1CodeEnum.restoreIpAddresses(s);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start)+"ms");
        while(true){

        }
    }
}
