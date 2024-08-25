package Leetcode100;
//把二叉搜索树转换为累加树
public class rehot93 {
    //反序中序遍历
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        traversal(root);
        return root;
    }
    public void traversal(TreeNode root){
        if(root==null){
            return;
        }
        traversal(root.right);
        sum+=root.val;
        root.val = sum;
        traversal(root.left);
    }
}
