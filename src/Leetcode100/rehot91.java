package Leetcode100;

//汉明距离
public class rehot91 {
    //不停用余2判断，除2（或右移迭代）
    public int hammingDistance(int x, int y) {
        int res = 0;
        while (x != 0 && y != 0) {
            if (x % 2 != y % 2) {
                res++;
            }
            x /= 2;
            y /= 2;
        }
        while (x != 0) {
            if (x % 2 == 1) {
                res++;
            }
            x /= 2;
        }
        while (y != 0) {
            if (y % 2 == 1) {
                res++;
            }
            y /= 2;
        }
        return res;
    }

    //使用异或操作和右移操作
    public int hammingDistance2(int x, int y) {
        int s = x ^ y, ret = 0;
        while (s != 0) {
            ret += s & 1;
            s >>= 1;
        }
        return ret;
    }
}
