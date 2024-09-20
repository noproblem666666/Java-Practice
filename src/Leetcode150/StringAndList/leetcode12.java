package Leetcode150.StringAndList;

import java.util.HashMap;
import java.util.Stack;

//整数转罗马数字
public class leetcode12 {
    public String intToRoman(int num) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "I");
        hashMap.put(4, "IV");
        hashMap.put(5, "V");
        hashMap.put(9, "IX");
        hashMap.put(10, "X");
        hashMap.put(40, "XL");
        hashMap.put(50, "L");
        hashMap.put(90, "XC");
        hashMap.put(100, "C");
        hashMap.put(400, "CD");
        hashMap.put(500, "D");
        hashMap.put(900, "CM");
        hashMap.put(1000, "M");

        //利用栈的先进后出，满足最后拼接的顺序
        Stack<Integer> stack = new Stack<>();
        int count = 1;
        while (num != 0) {
            int temp = num % 10;
            if (temp == 4 || temp == 9 || temp == 5) {
                //拼接之后不能continue，别忘了走最后的循环条件
                stack.push(temp * count);
            } else if (temp > 5) {
                //注意这里是先拼1再最后拼5，最后最后答案先拼5的罗马数字再拼1
                while (temp > 5) {
                    stack.push(count);
                    temp--;
                }
                stack.push(5 * count);
                temp -= 5;
            } else {
                while (temp > 0) {
                    stack.push(count);
                    temp--;
                }
            }
            num /= 10;
            count *= 10;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(hashMap.get(stack.pop()));
        }
        return sb.toString();
    }

    // 模拟相减
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman2(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    //硬编码数字
    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    public String intToRoman3(int num) {
        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }



}
