package Leetcode150.StringAndList;

//加油站（找油量柱状图的最低点，返回最低点的下一个点即可）
public class leetcode134 {
    //每个索引都测试走一圈是否能回来，超时
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int hasGas = 0;
            for (int j = 0; j < n; j++) {
                int index = (i + j) % n;
                hasGas = hasGas + gas[index] - cost[index];
                if(hasGas<0){
                    break;
                }
                if(j == n-1){
                    return i;
                }
            }
        }
        return -1;
    }

    //Todo：正确写法，亏空最严重的一个点必须放在最后一步走，等着前面剩余的救助
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int len = gas.length;
        int spare = 0;
        int minSpare = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < len; i++) {
            spare += gas[i] - cost[i];
            if (spare < minSpare) {
                minSpare = spare;
                minIndex = i;
            }
        }
        //如果剩余油量小于0，说明肯定不能绕一圈
        return spare < 0 ? -1 : (minIndex + 1) % len;
    }

    //与解法一类似但是降低时间复杂度，去掉了无意义的重试
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                //Todo： 如果x到达不了y+1，那么x-y之间的点也不可能到达y+1，因为中间任何一点的油都是拥有前面的余量的，所以下次遍历直接从y+1开始
                i = i + cnt + 1;
            }
        }
        return -1;
    }

}
