package MeiTuanTest;

import java.util.ArrayList;
import java.util.Scanner;

public class Test2CodeString {
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
    public ArrayList<String> restoreIpAddresses (String s) {
        ArrayList<String> res = new ArrayList<String>();
        dfs(s, res, 0, 0);
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
        Test2CodeString test2CodeString = new Test2CodeString();

        long start = 0;
        long end = 0;

        System.out.println("使用string拼接的的代码:");
        start = System.currentTimeMillis();
        for (int i = 0;i<100;i++){
            for (String s : test) {
                test2CodeString.restoreIpAddresses(s);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start)+"ms");
        while(true){

        }
    }

}
