package Leetcode100;

import java.util.ArrayDeque;
import java.util.Deque;

//二叉树的最近公共祖先
public class rehot67 {
    //使用双向队列记录每个结点到根结点的路径，之后从头开始比较
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> pPath = new ArrayDeque<>();
        Deque<TreeNode> qPath = new ArrayDeque<>();
        findPath(root,p,new ArrayDeque<>(),pPath);
        findPath(root,q,new ArrayDeque<>(),qPath);
        TreeNode res = root;
        while(!qPath.isEmpty()&&!pPath.isEmpty()){
            if(qPath.getFirst()!=pPath.getFirst()){
                return res;
            }
            res = qPath.removeFirst();
            pPath.removeFirst();
        }
        return res;
    }
    public void findPath(TreeNode root,TreeNode target,Deque<TreeNode> temp,Deque<TreeNode> res){
        if(root.val == target.val){
            temp.addLast(root);
            res.addAll(temp);
            return;
        }
        if(root.left!=null){
            temp.addLast(root);
            findPath(root.left,target,temp,res);
            temp.removeLast();
        }
        if(root.right!=null){
            temp.addLast(root);
            findPath(root.right,target,temp,res);
            temp.removeLast();
        }
    }

    //递归方法，最优方法
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            //只要当前根节点是p和q中的任意一个，就返回（因为不能比这个更深了，再深p和q中的一个就没了）
            return root;
        }
        //根节点不是p和q中的任意一个，那么就继续分别往左子树和右子树找p和q
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        //p和q都没找到，那就没有
        if(left == null && right == null) {
            return null;
        }
        //左子树没有p也没有q，就返回右子树的结果
        if (left == null) {
            return right;
        }
        //右子树没有p也没有q就返回左子树的结果
        if (right == null) {
            return left;
        }
        //左右子树都找到p和q了，那就说明p和q分别在左右两个子树上，所以此时的最近公共祖先就是root
        return root;
    }
}
