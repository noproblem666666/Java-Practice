package Leetcode150.StringAndList;

import java.util.Arrays;

//合并两个有序数组(不使用额外空间的做法)
public class leetcode88 {
    //nums1从后往前排，就可以尽可能多使用空间
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 =m-1;
        int p2 = n-1;
        //没必要交换，直接覆盖，遍历到需要覆盖的时候，值肯定已经用过了
        while(p1>=0&&p2>=0){
            nums1[p1+p2+1] = nums1[p1]<=nums2[p2]? nums2[p2--]:nums1[p1--];
        }
        //如果是nums1没有遍历完，就可以直接返回了
        while(p2>=0){
            nums1[p1+p2+1] = nums2[p2--];
        }
    }
}
