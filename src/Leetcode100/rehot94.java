package Leetcode100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//二叉树的直径（转化为求二叉树深度问题，求每个结点的左右子结点深度和）
public class rehot94 {
    //计算所有路径，超时
    List<List<TreeNode>> node = new ArrayList<>();
    List<TreeNode> temp = new ArrayList<>();
    int max=0;
    public int diameterOfBinaryTree(TreeNode root) {
        traversal(root);
        findMax();
        return max;
    }
    public void traversal(TreeNode root){
        if(root == null){
            node.add(new ArrayList<>(temp));
            return;
        }
        temp.add(root);
        traversal(root.left);
        traversal(root.right);
        temp.remove(root);
    }
    public void findMax(){
        for (List<TreeNode> treeNodes : node) {
            for (List<TreeNode> nodes : node) {
                Set<TreeNode> set = new HashSet<>();
                set.addAll(treeNodes);
                set.addAll(nodes);
                int left = treeNodes.size()+nodes.size()-set.size();
                max = Math.max(max,set.size()-left);
            }
        }
    }

    //用于记录最终结果
    int res=0;
    public int diameterOfBinaryTree2(TreeNode root) {
        if (root==null) return 0;
        dfs(root);
        return res;
    }
    int dfs(TreeNode root){
        if (root==null) return 0;
        int left=dfs(root.left);
        int right=dfs(root.right);
        res=Math.max(res,left+right);
        //返回值为该节点最大深度
        return Math.max(left,right)+1;
    }
}
