package Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//二叉树的最大深度
public class rehot42 {
    //把上一题的层次遍历稍微修改即可
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int max = 0;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            //每次遍历到下一层，就将深度加一
            max++;
            int index = 0;
            int len = queue.size();
            while(index<len){
                TreeNode node = queue.poll();

                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
                index++;
            }
        }
        return max;
    }

    //也可以使用递归，二叉树的深度等于其左右子树深度的最大值加一
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth2(root.left);
            int rightHeight = maxDepth2(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }


}

