package Leetcode150.BinaryTree;
//相同的树
public class leetcode100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //调用递归函数，比较左节点，右节点
        return dfs(p,q);
    }
    //另写一个递归函数，因为需要两个参数来做递归
    boolean dfs(TreeNode p, TreeNode q) {
        //递归的终止条件是两个节点都为空
        //或者两个节点中有一个为空
        //或者两个节点的值不相等
        if(p==null && q==null) {
            return true;
        }
        if(p==null || q==null) {
            return false;
        }
        if(p.val!=q.val) {
            return false;
        }
        //与对称树的区别就是递归函数参数中的左右比较次序
        return dfs(p.left,q.left) && dfs(p.right,q.right);
    }
}
