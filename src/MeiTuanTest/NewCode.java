package MeiTuanTest;

import java.util.*;

public class NewCode {
    //这是我改进的新的代码，这次不使用StringBuffer进行拼接，使用栈完成IP字段的存储，清除和最后的拼接
    public ArrayList<String> restoreIpAddresses(String s) {
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
                //这里别忘了i+1，否则会无限循环
                //Todo：使用while时如果用continue很容易忘记+1，用for循环不容易错过
                i++;
                continue;
            }
            DFS(s,step+1,index+i,deque,res);
            //递归后去掉本次递归加入的数字
            deque.removeLast();
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

        NewCode newCode = new NewCode();

        long start = 0;
        long end = 0;

        System.out.println("更新的代码:");
        start = System.currentTimeMillis();
        for (int i = 0;i<100;i++){
            for (String s : test) {
                newCode.restoreIpAddresses(s);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end-start)+"ms");
        while(true){

        }
    }
}
