package Leetcode;

import java.util.*;

//前k个高频元素
public class rehot83 {
    public int[] topKFrequent(int[] nums, int k) {
        //设置一个map集合,key存放数字,value存放出现次数
        Map<Integer,Integer> map = new HashMap<>();
        //统计出现次数
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //建立一个小根堆,用来存放key值,堆内元素按照key对应的value值从大到小排序
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer a,Integer b){
                return map.get(a) - map.get(b);
            }
        });
        //将map中的数字,插入到小根堆中
        for(Integer key:map.keySet()){
            if(queue.size() < k){
                queue.add(key);
            }else if(map.get(key) > map.get(queue.peek())){
                queue.poll();
                queue.add(key);
            }
        }
        //将小根堆中的k个数字放到数组中
        int [] res = new int[k];
        int index = 0;
        while(!queue.isEmpty()){
            res[index++] = queue.poll();
        }
        return res;
    }

    //计数排序，桶排序
    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        List<Integer>[] list = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int i = map.get(key);
            if (list[i] == null) {
                list[i] = new ArrayList();
            }
            list[i].add(key);
        }
        // 倒序遍历数组获取出现顺序从大到小的排列
        int[] res = new int[k];
        int count = 0;
        for (int i = list.length - 1; i >= 0 ; i--) {
            if (list[i] == null) continue;
            List<Integer> listSon = list[i];
            for (int j = 0; j < listSon.size(); j++) {
                int key = listSon.get(j);
                if (count < k) {
                    res[count] = key;
                    count++;
                }
            }
        }
        return res;
    }
}
