package Leetcode100;
//搜索二维矩阵（二）（注意观察矩阵从左下角开始往上比和往右比的数的特征。右上角也可以）
public class rehot70 {
    //Z字形查找左下角开始比较
    //注意区分行和列对应的数
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean res = false;
        int i = m-1;
        int j = 0;
        while(i>=0&&j<n&&matrix[i][j]!= target){
            if(matrix[i][j]<target){
                j++;
            }else{
                i--;
            }
        }
        if(i>=0&&j<n&&matrix[i][j] == target){
            res = true;
        }
        return res;
    }

    //对每一行进行二分查找
    public boolean searchMatrix2(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


}
