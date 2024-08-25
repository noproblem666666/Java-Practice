package Leetcode100;


import java.util.*;

//二叉树的中序遍历
public class rehot37 {
    //递归
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        inorder(root,res);
        return res;
    }
    public void inorder(TreeNode root,List<Integer> res){
        if(root.left!=null){
            inorder(root.left,res);
        }
        res.add(root.val);
        if(root.right!=null){
            inorder(root.right,res);
        }
    }

    //使用栈迭代
    public List<Integer> inorderTraversal2(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(root);
        while(!stack.isEmpty()){
            while(root.left!=null){
                stack.addLast(root.left);
                root = root.left;
            }
            TreeNode node = stack.pollLast();
            res.add(node.val);
            if(node.right!=null){
                stack.addLast(node.right);
                //别忘了把指针指向新的结点
                root = node.right;
            }
        }
        return res;
    }
}
