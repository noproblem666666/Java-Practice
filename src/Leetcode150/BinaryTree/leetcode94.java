package Leetcode150.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//二叉树的中序遍历
public class leetcode94 {
    //递归写法
    public List<Integer> inorderTraversal(TreeNode root) {
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
        res.add(root.val);
        dfs(root.right,res);
    }

    //Todo:非递归写法
    public List<Integer> inorderTraversal2(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while(!stack.isEmpty() || root!=null){
            //不断往左子树方向走，每走一次就将当前节点保存到栈中
            //这是模拟递归的调用
            if(root!=null){
                stack.push(root);
                root = root.left;
            //当前节点为空，说明左边走到头了，从栈中弹出节点并保存
            //然后转向右边节点，继续上面整个过程
            }else{
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}
