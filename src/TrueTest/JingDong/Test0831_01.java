package TrueTest.JingDong;

import java.util.Scanner;

public class Test0831_01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String s = in.nextLine();
        char[] chars = s.toCharArray();
        char[] test = new char[10];
        int[] count = new int[chars.length];
        for(int i = 0;i<chars.length;i++){
            count[i] = chars[i]-'a';
        }
        for(int i = chars.length-1;i>=0;i--){
            count[i]+=1;
            if(count[i]>=26){
                if(i==0){
                    System.out.println(-1);
                    return;
                }
                count[i] = 0;
            }else{
                break;
            }
        }
        for(int i = 0;i<chars.length;i++){
            chars[i] = (char)(count[i] + 97);
        }
        System.out.println(chars);

    }
}
