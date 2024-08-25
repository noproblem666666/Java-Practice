package Leetcode100;

import java.util.HashMap;
import java.util.Map;

//路径总和（三）（前缀和）
public class rehot88 {
    Map<Long, Integer> prefixMap;  //存储结点前缀和，key是前缀，value是前缀结点个数（注意不同子路径恢复状态）
    int target;

    public int pathSum(TreeNode root, int sum) {
        prefixMap = new HashMap<>();
        target = sum;
        //后面带一个L表示是Long类型
        prefixMap.put(0L, 1);
        return recur(root, 0);
    }

    private int recur(TreeNode node, long curSum) {
        if (node == null) {
            return 0;
        }
        long res = 0;
        curSum += node.val;

        res += prefixMap.getOrDefault(curSum - target, 0);
        prefixMap.put(curSum, prefixMap.getOrDefault(curSum, 0) + 1);

        long left = recur(node.left,curSum);
        long right = recur(node.right,curSum);
        res = res+left+right;
        //！！！退出递归时还原状态
        prefixMap.put(curSum,prefixMap.get(curSum)-1);
        return (int)res;
    }

    //双重递归，对于每个结点，计算其所有子路径
    int ans, t;
    public int pathSum2(TreeNode root, int _t) {
        t = _t;
        dfs1(root);
        return ans;
    }
    void dfs1(TreeNode root) {
        if (root == null) return;
        dfs2(root, root.val);
        dfs1(root.left);
        dfs1(root.right);
    }
    void dfs2(TreeNode root, long val) {
        if (val == t) ans++;
        if (root.left != null) dfs2(root.left, val + root.left.val);
        if (root.right != null) dfs2(root.right, val + root.right.val);
    }

}
