package Leetcode;
//打家劫舍（三）(动态规划，递归)
public class rehot81 {
    public int rob(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0],result[1]);
    }

    //由于是树形的，所以动态规划的状态是每个树结点的抢劫与否，用递归来结算
    private int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }
}
