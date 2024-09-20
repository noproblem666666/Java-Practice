package Leetcode150.BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//二叉树的后序遍历
public class leetcode145 {
    //递归写法
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        dfs(root,res);
        return res;
    }
    public void dfs(TreeNode root,List<Integer> res){
        if(root==null){
            return;
        }
        dfs(root.left,res);
        dfs(root.right,res);
        res.add(root.val);
    }

    //Todo：非递归写法
    public List<Integer> postorderTraversal2(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            //注意这里和前序的区别，先左后右，这样遍历顺序就变成了中右左，再倒置一次就变成了后序的左右中
            if(node.left!=null){
                stack.push(node.left);
            }
            if(node.right!=null){
                stack.push(node.right);
            }
        }
        //最后需要将结果倒置一次
        Collections.reverse(res);
        return res;
    }
}
