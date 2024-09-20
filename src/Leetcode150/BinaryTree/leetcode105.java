package Leetcode150.BinaryTree;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// 从前序与中序遍历序列构造二叉树
public class leetcode105 {

    //自己写的递归
    public TreeNode produceTree(int[] preorder, int[] inorder, int index, int left, int right){
        //此时直接返回当前结点（这一步其实是多余的，因为递归算法最后肯定会返回当前结点）
        if(left==right){
            return new TreeNode(inorder[left]);
        }
        //检查先序遍历的序号是否会越界，是则返回空
        if(index>preorder.length-1){
            return null;
        }
        int temp = preorder[index];
        //找到先序结点在中序遍历序列中的位置
        //Todo:找位置可以用一个哈希表来加速
        int pre_index = 0;
        for(int i = 0;i<inorder.length;i++){
            if(inorder[i] == temp){
                pre_index = i;
                break;
            }
        }
        //如果找到的位置超出当前左右边界，则说明该结点也应该为空
        if(pre_index>right||pre_index<left){
            return null;
        }
        //计算左子树结点的个数，用于确定右结点先序序列的位置
        int count = pre_index-left;

        TreeNode node = new TreeNode(temp);
        node.left = produceTree(preorder, inorder, index+1, left,pre_index-1);
        node.right = produceTree(preorder, inorder, index+1+count, pre_index + 1, right);
        return node;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode res = produceTree(preorder, inorder, 0, 0, preorder.length-1);
        return res;
    }

    //答案写法递归
    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }


    //迭代写法
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }



}
