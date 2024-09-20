package Leetcode150.BinaryTree;
//路径总和
public class leetcode112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return root.val == sum;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    //这种写法不正确，会多算叶子结点，必须是没有子结点的结点才算叶子结点
    public boolean hasPathSum2(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        return dfs(root.left,sum-root.val) || dfs(root.right,sum- root.val);
    }
    public boolean dfs(TreeNode root,int sum){
        if(root == null){
            return sum==0;
        }
        return dfs(root.left,sum-root.val) || dfs(root.right,sum- root.val);
    }
}
