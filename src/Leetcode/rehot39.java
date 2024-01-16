package Leetcode;
//验证二叉搜索树
public class rehot39 {
    //数字范围很大，用long。递归，在左子树中寻找最大值看它是否大于等于根节点，在右子树中寻找最小值看它是否小于等于根节点
    boolean isBST = true;
    public boolean isValidBST(TreeNode root) {
        isValid(root);
        return isBST;
    }

    public void isValid(TreeNode root){
        if(root==null){
            return;
        }
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        //在左子树中寻找最大值看它是否大于等于根节点
        if(root.left!=null){
            min = findMax(root.left);
        }
        //在右子树中寻找最小值看它是否小于等于根节点
        if(root.right!=null){
            max = findMin(root.right);
        }
        if(min>=root.val||max <= root.val){
            isBST = false;
        }
        isValid(root.left);
        isValid(root.right);
    }

    public int findMin(TreeNode root) {
        while(root.left!=null){
            root = root.left;
        }
        return root.val;
    }

    public int findMax(TreeNode root){
        while(root.right!=null){
            root = root.right;
        }
        return root.val;
    }

    //保留前序节点的数字，用中序遍历，如果当前节点小于等于
    long pre = Long.MIN_VALUE;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST2(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST2(root.right);
    }

    //递归
    public boolean isValidBST3(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }




}
