package Leetcode150.BinaryTree;

import java.util.HashMap;

//从中序与后序遍历序列构造二叉树(与105逻辑一样，需要注解递归出口和边界条件设定)
//Todo:一定要想好并统一数组左右开闭，否则答案会错误
public class leetcode106 {
    HashMap<Integer, Integer> index = new HashMap<>();
    public TreeNode dfs(int[] inorder, int[] postorder, int in_left, int in_right, int post_left, int post_right) {
        //  >的条件前提是统一左闭右闭
        if (in_left > in_right) {
            return null;
        }
        //这里的下标也是右闭
        int val = postorder[post_right];
        int in_index = index.get(val);
        //左子树中的结点个数
        int count = in_index - in_left;
        TreeNode node = new TreeNode(val);
        //递归条件中也要统一左闭右闭
        node.left = dfs(inorder, postorder, in_left, in_index - 1, post_left, post_left + count-1);
        node.right = dfs(inorder, postorder, in_index + 1, in_right, post_left + count, post_right - 1);
        return node;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        int len = inorder.length;
        for (int i = 0; i < len; i++) {
            index.put(inorder[i], i);
        }
        //这里传入的数组是左闭右闭
        return dfs(inorder, postorder, 0, len-1, 0, len-1);
    }
}
