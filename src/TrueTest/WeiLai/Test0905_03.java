package TrueTest.WeiLai;

import java.util.Scanner;

public class Test0905_03 {
    //打印超级圣诞树，尝试缓存上一次的每个图形，再在下一次拼接两次
    //最开始基础图形  *
    // * *
    //     * * *
    //之后拼接最后的几个树干
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}
