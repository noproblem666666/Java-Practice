package Leetcode100;

import java.util.*;

//找到所有数组中消失的数字（原地修改数组，不使用额外空间）
public class rehot90 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < n; i++){
            //把相应的nums中的数对应的-1下标位置，乘以-1 表明这个下标也就是这个数出现过了
            if(nums[Math.abs(nums[i]) - 1] > 0)
                nums[Math.abs(nums[i]) - 1] *= -1;
        }
        for(int i = 0; i < n; i++){
            if(nums[i] > 0)
                list.add(i+1);
        }
        return list;
    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        //执行完第一次后，先执行i++，再判断表达式是否要继续执行
        for(int i = 1;i<=nums.length;i++){
            if(!set.contains(i)){
                result.add(i);
            }
        }
        return result;
    }
}
