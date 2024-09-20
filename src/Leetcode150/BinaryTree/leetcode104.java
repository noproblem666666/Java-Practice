package Leetcode150.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode{
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

//二叉树的最大深度
public class leetcode104 {
    //深度优先遍历，递归
    public int maxDepth(TreeNode root) {
        //递归出口
        if(root == null){
            return 0;
        }
        //递归求最大深度，每一层的深度都是子节点的最大深度加一
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    //广度优先遍历，队列
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            //记录每一层是否遍历完成的标志
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }
}
