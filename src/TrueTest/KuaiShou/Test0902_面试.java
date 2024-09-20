package TrueTest.KuaiShou;
//根据先序和中序遍历构造树
class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left,TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Test0902_面试 {

        public TreeNode produceTree(int[] preorder,int[] inorder,int index,int left,int right){
            //此时直接返回当前结点
            if(left==right){
                return new TreeNode(inorder[left]);
            }
            //检查先序遍历的序号是否会越界，是则返回空
            if(index>preorder.length-1){
                return null;
            }
            int temp = preorder[index];
            //找到先序结点在中序遍历序列中的位置
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
        public TreeNode deduceTree(int[] preorder, int[] inorder) {
            TreeNode res = produceTree(preorder, inorder, 0, 0, preorder.length-1);
            return res;
        }

}
