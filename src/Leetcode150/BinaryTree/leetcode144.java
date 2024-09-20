package Leetcode150.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//二叉树的前序遍历
public class leetcode144 {
    //递归写法
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        dfs(root,res);
        return res;
    }
    public void dfs(TreeNode root ,List<Integer> res){
        if (root == null) {
            return;
        }
        res.add(root.val);
        dfs(root.left,res);
        dfs(root.right,res);
    }

    //Todo:非递归写法
    public List<Integer> preorderTraversal2(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            //注意这里先放右节点再放左节点
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
        return res;
    }


}
