package Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//二叉树的层序遍历
public class rehot41 {
    //记录每一层的长度，遍历到指定长度就开始下一层
    //也可以使用两个队列进行不同层次划分，但是效率更低
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new ArrayDeque<>();

        List<List<Integer>> res = new ArrayList<>();

        queue.add(root);
        while(!queue.isEmpty()){
            int index = 0;
            int len = queue.size();
            List<Integer> temp = new ArrayList<>();
            while(index<len){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
                index++;
            }
            res.add(temp);
        }
        return res;
    }
}
