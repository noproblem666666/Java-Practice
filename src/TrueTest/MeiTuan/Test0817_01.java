package TrueTest.MeiTuan;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正则表达式匹配
public class Test0817_01 {
    public boolean isEmail(String str){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\\\\.com$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    public boolean isIP(String str){
        Pattern pattern = Pattern.compile("^(\\d{1,2}|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d{2}|2[0-4]\\d|25[0-5])$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    public boolean isPhone(String str){
        Pattern pattern = Pattern.compile("^\\+\\d+-\\d+-[\\d#]+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //忽略整数后的换行符
        scanner.nextLine();

        //之后再逐个输入语句并进行判断




        scanner.close();
    }
}
