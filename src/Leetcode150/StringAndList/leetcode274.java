package Leetcode150.StringAndList;

import java.util.Arrays;
import java.util.HashMap;

//H 指数
public class leetcode274 {
    // 使用hashmap计数
    public int hIndex(int[] citations) {
        //记录发表的每个引用次数的文章个数
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int citation : citations) {
            hashMap.put(citation,hashMap.getOrDefault(citation,0)+1);
        }
        System.out.println(hashMap);
        int n = citations.length;
        //记录大于等于i的文章有多少个，然后从前往后逐个递减即可
        int[] count = new int[n+1];
        count[0] = n;
        for(int i = 1;i<n+1;i++){
            count[i] = count[i-1]-hashMap.getOrDefault(i-1,0);
        }
        System.out.println(Arrays.toString(count));
        for(int i = n;i>=0;i--){
            if(count[i]>=i){
                return i;
            }
        }
        return 0;
    }

    //排序，从后往前遍历，如果有一个数大于h，那么这个数字之后的所有数肯定也都大于h（也可逆序排序，从前往后遍历）
    //这样就是逐步找到大于等于1的文章数至少有一篇，大于等于2的文章数至少有两篇
    public int hIndex2(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    //计数排序，和方法一类似，但是没用哈希表，大于n的也记在n下标里就行了
    public int hIndex3(int[] citations) {
        int n = citations.length, tot = 0;
        int[] counter = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n) {
                counter[n]++;
            } else {
                counter[citations[i]]++;
            }
        }
        for (int i = n; i >= 0; i--) {
            tot += counter[i];
            if (tot >= i) {
                return i;
            }
        }
        return 0;
    }

    //二分查找法
    //设查找范围的初始左边界 left 为 0，初始右边界 right 为 n。
    // 每次在查找范围内取中点 mid，同时扫描整个数组，判断是否至少有 mid 个数大于 mid。
    // 如果有，说明要寻找的 h 在搜索区间的右边，反之则在左边。
    public int hIndex4(int[] citations) {
        int left=0,right=citations.length;
        int mid=0,cnt=0;
        while(left<right){
            // +1 防止死循环
            mid=(left+right+1)>>1;
            cnt=0;
            for(int i=0;i<citations.length;i++){
                if(citations[i]>=mid){
                    cnt++;
                }
            }
            if(cnt>=mid){
                // 要找的答案在 [mid,right] 区间内
                left=mid;
            }else{
                // 要找的答案在 [0,mid) 区间内
                right=mid-1;
            }
        }
        return left;
    }

}
