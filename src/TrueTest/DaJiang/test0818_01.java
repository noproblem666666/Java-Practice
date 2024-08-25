package TrueTest.DaJiang;

import java.util.*;
import java.util.stream.Stream;

public class test0818_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int addTimes = scanner.nextInt();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < addTimes; i++) {
            int index = scanner.nextInt();
            hashMap.put(index, hashMap.getOrDefault(index, 0) + 1);
        }
        int leaveTimes = scanner.nextInt();
        for (int i = 0; i < leaveTimes; i++) {
            int index = scanner.nextInt();
            hashMap.put(index, hashMap.getOrDefault(index, 1) - 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        List<Map.Entry<Integer, Integer>> entries1 = entries.stream().sorted(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        }).toList();
        int[] res = new int[16];
        //其实不对hashmap的键值对排序也可以，直接相加
        for (Map.Entry<Integer, Integer> entry : entries1) {
            if (entry.getValue() > 0 && entry.getValue() < 16) {
                res[entry.getValue()] += 1;
            }
        }
        for (int i = 1; i < 16; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
