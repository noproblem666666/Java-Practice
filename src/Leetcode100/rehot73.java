package Leetcode100;
//移动零
public class rehot73 {
    //用一个数组记录每个数需要往前移动的步数，之后从前往后移动
    //浪费了数组空间并且多遍历了一次
    public void moveZeroes(int[] nums) {
        int[] count = new int[nums.length];
        count[0] = 0;
        for(int i = 1;i<nums.length;i++){
            if(nums[i-1]==0){
                count[i] = count[i-1]+1;
            }else{
                count[i] = count[i-1];
            }
        }
        for(int i = 1;i< nums.length;i++){
            nums[i-count[i]] = nums[i];
        }
        //再把最后的部分全部置为0
        for(int i = nums.length-count[nums.length-1];i<nums.length;i++){
            nums[i] = 0;
        }
    }

    //答案的写法，第一次遍历的时候就用一个指针记录当前数需要左移的步数，直接左移
    public void moveZeroes2(int[] nums) {
        if(nums==null) {
            return;
        }
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for(int i=0;i<nums.length;++i) {
            if(nums[i]!=0) {
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完了，剩下的都是0了
        //所以第二次遍历把末尾的元素都赋为0即可
        for(int i=j;i<nums.length;++i) {
            nums[i] = 0;
        }
    }

    //一次遍历的写法，借鉴了快排思想（看图好理解）
    public void moveZeroes3(int[] nums) {
        if(nums==null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for(int i=0;i<nums.length;i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if(nums[i]!=0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }



}
