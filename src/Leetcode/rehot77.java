package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//删除无效的括号(搜索+剪枝)
public class rehot77 {
    Set<String> set = new HashSet<>();
    // n：字符串长度；max：最大括号数（单括号）；maxPathLen：记录「爆搜」过程中的最大路径子串的长度
    int n, max, maxPathLen;
    String s;

    public List<String> removeInvalidParentheses(String _s) {
        s = _s;
        n = s.length();
        int left = 0, right = 0;
        // 统计左右括号数量
        for (char c : s.toCharArray()) {
            if (c == '(') left++;
            else if (c == ')') right++;
        }

        max = Math.min(left, right);
        dfs(0, "", 0);
        return new ArrayList<>(set);    // 将Set集合转为List返回
    }

    /**
     * 遍历 _s 字符串，记录有效路径
     * @param curCharIndex 当前遍历的字符下标
     * @param path 遍历时的路径（括号组合字符串）
     * @param score 分数，用于标记左右括号的得分
     */
    //Todo：由于我们将退出条件的判断放在了函数开始，因此在递归入口处可以直接执行所有的情况
    private void dfs(int curCharIndex, String path, int score) {
        // 剪枝：合法路径的得分范围为 0 <= score <= max
        if (score < 0 || score > max) return;

        // 当前下标等于字符串长度（遍历完）时，搜索完成，保存合法结果进set集合
        if (curCharIndex == n) {
            // 总得分为0括号才匹配，当前路径长度 >= 最大路径子串的长度才记录或者更新
            if (score == 0 && path.length() >= maxPathLen) {
                // 当前路径长度大于最大路径子串的长度时，说明有更长的合法串，清除set
                if (path.length() > maxPathLen) set.clear();

                // 更新最大路径子串长度为当前路径长度，并且添加进集合
                maxPathLen = path.length();
                set.add(path);
            }
            return;    // 搜索完成，退出
        }

        char c = s.charAt(curCharIndex);     // 获取当前字符

        // 每一种选择都对应可选可不选
        if (c == '(') {         // 选左括号，score + 1；不选score不变
            dfs(curCharIndex + 1, path + c, score + 1);
            dfs(curCharIndex + 1, path, score);
        } else if (c == ')') {      // 选右括号，score - 1；不选score不变
            dfs(curCharIndex + 1, path + c, score - 1);
            dfs(curCharIndex + 1, path, score);
        } else {        // 普通字符，score不变
            dfs(curCharIndex + 1, path + c, score);
        }
    }

    Set<String> set2 = new HashSet<>();
    // n：字符串长度；max：最大括号数（单括号）；maxPathLen：记录「爆搜」过程中的最大路径子串的长度
    int n2, max2, maxPathLen2;
    String s2;

    public List<String> removeInvalidParentheses2(String _s) {
        s = _s;
        n = s.length();
        int left = 0, right = 0;

        // 统计多余的括号数量
        for (char c : s.toCharArray()) {
            if (c == '(') left++;
            else if (c == ')') {
                if (left != 0) left--;
                else right++;
            }
        }
        maxPathLen = n - left - right;      // 提前更新 maxPathLen

        // 统计左右括号数量
        int left2 = 0, right2 = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') left2++;
            else if (c == ')') right2++;
        }

        max = Math.min(left2, right2);
        dfs(0, "", left, right, 0);
        return new ArrayList<>(set);    // 将Set集合转为List返回
    }

    /**
     * 遍历 _s 字符串，记录有效路径
     * @param curCharIndex 当前遍历的字符下标
     * @param path 遍历时的路径（括号组合字符串）
     * @param left 多余的左括号数量
     * @param right 多余的右括号数量
     * @param score 分数，用于标记左右括号的得分
     */
    private void dfs(int curCharIndex, String path, int left, int right, int score) {
        // 剪枝：合法路径的得分范围为 0 <= score <= max；多余的括号数量为负数，说明删多了，不符合
        if (left < 0 || right < 0 || score < 0 || score > max) return;

        if (left == 0 && right == 0) {
            // 多余的括号为0，且当前路径长度等于最大路径子串的长度，则符合
            if (path.length() == maxPathLen) {
                set.add(path);
            }
        }

        if (curCharIndex == n) return;      // 搜索完毕，退出（放在此处是为了记录完最后一个字符）

        char c = s.charAt(curCharIndex);     // 获取当前字符

        // 每一种选择都对应 添加/不添加
        if (c == '(') {         // 添加左括号，score + 1；不添加score不变，多余的左括号数量-1
            dfs(curCharIndex + 1, path + c, left, right, score+ 1);
            dfs(curCharIndex + 1, path, left - 1, right, score);
        } else if (c == ')') {      // 添加右括号，score - 1；不添加score不变，多余的右括号数量-1
            dfs(curCharIndex + 1, path + c, left, right, score - 1);
            dfs(curCharIndex + 1, path, left, right - 1, score);
        } else {        // 普通字符，score不变
            dfs(curCharIndex + 1, path + c, left, right, score);
        }
    }
}
