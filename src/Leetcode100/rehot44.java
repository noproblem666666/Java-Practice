package Leetcode100;

//二叉树展开为链表
public class rehot44 {
    //递归
    public void flatten(TreeNode root) {
        if(root==null){
            return;
        }
        //Todo：由于下面的递归函数只能递归将传入根节点的左子树变为链表加入右子树，因此还需要迭代根节点的右子树上的所有节点
        while(root!=null){
            flat(root);
            root=root.right;
        }
    }
    //递归的将左子树变为链表加入右子树
    public void flat(TreeNode root) {
        if (root.left != null) {
            flat(root.left);
        }
        TreeNode temp = root.right;
        root.right = root.left;
        //Todo：别忘了把左子树变为null
        root.left=null;
        while (root.right != null) {
            root = root.right;
        }
        root.right = temp;
    }

    //迭代方法，每次寻找左子树最右边的节点，将原来的右子树接到左子树的最右边节点，再考虑新的右子树的根节点
    public void flatten2(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                //别忘了将左子树断开
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

}
