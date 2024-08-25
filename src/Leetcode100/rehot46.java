package Leetcode100;
//二叉树的最大路径和
public class rehot46 {
    //递归
    //用于在每层递归比较含有根节点的最大值
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPath(root);
        return max;
    }
    public int maxPath(TreeNode root){
        if(root==null){
            return 0;
        }
        int left =maxPath(root.left);
        int right = maxPath(root.right);
        int sum = root.val+left+right;
        max = Math.max(max,sum);
        //向上传递的时候要去掉一个更小的节点，避免节点重复使用
        sum-=Math.min(left,right);
        return Math.max(sum, 0);
    }
}
