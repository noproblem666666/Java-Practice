package Leetcode;

import java.util.*;

//课程表
public class rehot61 {
    //拓扑（时间复杂度较高）
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //记录每个课程目前的前置课程数
        HashMap<Integer,Integer> count = new HashMap<>();
        //记录每个课程的具体前缀课程
        HashMap<Integer, ArrayList<Integer>> courses = new HashMap<>();
        //提前对每个课程都初始化count，防止有的课程没有count记录导致无法遍历到
        for (int i = 0; i < numCourses; i++) {
            count.put(i,0);
        }
        for (int[] prerequisite : prerequisites) {
            int m = prerequisite[0];
            int n = prerequisite[1];
            count.put(m,count.getOrDefault(m,0)+1);
            if(!courses.containsKey(m)){
                courses.put(m,new ArrayList<>());
            }
            courses.get(m).add(n);
        }
        while(!count.isEmpty()){
            int i = -1;
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                //找到当前没有前置课程的课程
                if(entry.getValue()==0){
                    i = entry.getKey();
                    break;
                }
            }
            if(i!=-1){
                count.remove(i);
                for (Map.Entry<Integer, ArrayList<Integer>> integerArrayListEntry : courses.entrySet()) {
                    //删掉前缀里含有的对应课程，count里也减去相应数字
                    if(integerArrayListEntry.getValue().contains(i)){
                        //Todo:注意这里删的是元素不是下标，防止语义混乱，转换为Integer类型
                        integerArrayListEntry.getValue().remove(Integer.valueOf(i));
                        int j = integerArrayListEntry.getKey();
                        count.put(j,count.get(j)-1);
                    }
                }
            }else{
                return false;
            }
        }
        return true;
    }

    //同样是拓扑，答案使用队列来不断存储入度为0的点，效率会高很多
    // 节点的入度: 使用数组保存每个节点的入度,
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        // 1.课号和对应的入度
        Map<Integer, Integer> inDegree = new HashMap<>();
        // 将所有的课程先放入
        for (int i = 0; i < numCourses; i++) {
            inDegree.put(i, 0);
        }
        // 2.依赖关系, 依赖当前课程的后序课程
        Map<Integer, List<Integer>> adj = new HashMap<>();



        // 初始化入度和依赖关系
        for (int[] relate : prerequisites) {
            // (3,0), 想学3号课程要先完成0号课程, 更新3号课程的入度和0号课程的依赖(邻接表)
            int cur = relate[1];
            int next = relate[0];
            // 1.更新入度
            inDegree.put(next, inDegree.get(next) + 1);
            // 2.当前节点的邻接表
            if (!adj.containsKey(cur)) {
                adj.put(cur, new ArrayList<>());
            }
            adj.get(cur).add(next);
        }

        // 3.BFS, 将入度为0的课程放入队列, 队列中的课程就是没有先修, 可以学的课程
        Queue<Integer> q = new LinkedList<>();
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                q.offer(key);
            }
        }
        // 取出一个节点, 对应学习这门课程.
        // 遍历当前邻接表, 更新其入度; 更新之后查看入度, 如果为0, 加入到队列
        while (!q.isEmpty()) {
            int cur = q.poll();
            // 遍历当前课程的邻接表, 更新后继节点的入度
            if (!adj.containsKey(cur)) {
                continue;
            }
            List<Integer> successorList = adj.get(cur);

            for (int k : successorList) {
                inDegree.put(k, inDegree.get(k) - 1);
                if (inDegree.get(k) == 0) {
                    q.offer(k);
                }
            }
        }

        // 4.遍历入队, 如果还有课程的入度不为0, 返回fasle
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) != 0) {
                return false;
            }
        }
        return true;
    }

    //递归
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        int[] flags = new int[numCourses];
        for(int[] cp : prerequisites)
            adjacency.get(cp[1]).add(cp[0]);
        for(int i = 0; i < numCourses; i++)
            if(!dfs(adjacency, flags, i)) return false;
        return true;
    }
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if(flags[i] == 1) return false;  //先判断再修改标志位
        if(flags[i] == -1) return true;  //别的dfs路径访问过了，我不需要访问了
        flags[i] = 1;   //只有这个标志位是干净的，别人还没有动过，我才能标记为1，说明本次dfs我遍历过它
        for(Integer j : adjacency.get(i))
            if(!dfs(adjacency, flags, j))
                return false;
        flags[i] = -1;    //只有一次DFS完整结束了，才能执行到这一步，标记为-1，说明这条路没问题，再遇到不需要遍历了
        return true;
    }


}
