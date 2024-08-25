package TrueTest.MeiTuan;

import java.util.*;

public class PasswordTest {
    //还需要考虑去重，相同的密码只会尝试一次
    public int[] getTestTimes(int n, String target, String[] passwords) {
        Set<String> set = new HashSet<>();
        //最大长度确定时可以用数组，更加方便
        int[] count = new int[1001];
        for (String password : passwords) {
            if(set.contains(password)){
                continue;
            }
            int len = password.length();
            count[len]++;
            set.add(password);
        }
        int targetLen = target.length();
        int res = 0;
        for (int i = 0; i < targetLen; i++) {
            res += count[i];
        }
        return new int[]{res + 1, res + count[targetLen]};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String ans = scanner.next();
        //用哈希表存储每个长度的字符串，集合去重复
        Map<Integer, Set<String>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String p = scanner.next();
            pos.computeIfAbsent(p.length(), k -> new HashSet<>()).add(p);
        }
        //也可以对键进行排序，用键获取，不用对键值对进行排序
        List<Map.Entry<Integer, Set<String>>> sortedPos = new ArrayList<>(pos.entrySet());
        sortedPos.sort(Map.Entry.comparingByKey());
        //逆序排序的写法，最好掌握万金油的重写比较器写法
        //sortedPos.sort(Map.Entry.<Integer,Set<String>>comparingByKey().reversed());
        int step = 0;
        int MIN = -1, MAX = -1;
        for (Map.Entry<Integer, Set<String>> entry : sortedPos) {
            Set<String> v = entry.getValue();
            if (v.contains(ans)) {
                MIN = step + 1;
                MAX = step + v.size();
            } else {
                step += v.size();
            }
        }

        System.out.println(MIN + " " + MAX);

        scanner.close();
    }
}
