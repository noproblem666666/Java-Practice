package Leetcode150.StringAndList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//多数元素(多数元素出现次数大于数组长度的一半)
public class leetcode169 {
    //Boyer-Moore 投票算法
    public int majorityElement(int[] nums) {
        int count = 1;
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //每次都和当前的多数元素做比较，最后记录的肯定是整个数组的多数元素
            if(nums[i]!=temp){
                count--;
            }else{
                count++;
            }
            if(count==0){
                temp = nums[i];
                count = 1;
            }
        }
        return temp;
    }

    //哈希表计数
    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    //排序取中位数
    public int majorityElement3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //随机挑选验证
    private int randRange(Random rand, int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private int countOccurences(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    public int majorityElement4(int[] nums) {
        Random rand = new Random();

        int majorityCount = nums.length / 2;

        while (true) {
            int candidate = nums[randRange(rand, 0, nums.length)];
            if (countOccurences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }

    //分治法，一分为二后，多数元素必定是其中一边的多数元素，因为出现次数大于数组的一半
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElement5(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

}
