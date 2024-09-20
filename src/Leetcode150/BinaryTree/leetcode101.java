package Leetcode150.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//对称二叉树
public class leetcode101 {
    //额外知识：双端队列的两个实现类LinkedList和ArrayDeque，前者可以入队null，后者不能入队null，否则报错
    //Todo:这种写法是错误的，因为没有要求所有的子树也都是对称
    public boolean isSymmetric(TreeNode root){
        if(root ==null||(root.left==null&&root.right==null)){
            return true;
        }
        if(root.left==null || root.right == null){
            return false;
        }
        if(root.left.val!=root.right.val){
            return false;
        }
        return isSymmetric(root.left)&&isSymmetric(root.right);
    }

    //用队列保存节点，替代递归
    public boolean isSymmetric2(TreeNode root) {
        if(root==null || (root.left==null && root.right==null)) {
            return true;
        }
        //用队列保存节点
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while(queue.size()>0) {
            //从队列中取出两个节点，再比较这两个节点
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            //如果两个节点都为空就继续循环，两者有一个为空就返回false
            if(left==null && right==null) {
                continue;
            }
            if(left==null || right==null) {
                return false;
            }
            if(left.val!=right.val) {
                return false;
            }
            //Todo：注意放入顺序
            //将左节点的左孩子， 右节点的右孩子放入队列
            queue.add(left.left);
            queue.add(right.right);
            //将左节点的右孩子，右节点的左孩子放入队列
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }

    //Todo：正确写法
    public boolean isSymmetric3(TreeNode root) {
        if(root==null) {
            return true;
        }
        //调用递归函数，比较左节点，右节点
        return dfs(root.left,root.right);
    }
    //另写一个递归函数，因为需要两个参数来做递归
    boolean dfs(TreeNode left, TreeNode right) {
        //递归的终止条件是两个节点都为空
        //或者两个节点中有一个为空
        //或者两个节点的值不相等
        if(left==null && right==null) {
            return true;
        }
        if(left==null || right==null) {
            return false;
        }
        if(left.val!=right.val) {
            return false;
        }
        //再递归的比较 左节点的左孩子 和 右节点的右孩子
        //以及比较  左节点的右孩子 和 右节点的左孩子
        return dfs(left.left,right.right) && dfs(left.right,right.left);
    }

}
