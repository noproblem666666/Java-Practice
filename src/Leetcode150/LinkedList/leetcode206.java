package Leetcode150.LinkedList;
//反转链表（双指针法和递归法）
public class leetcode206 {
    //双指针法
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    //递归法
    public ListNode reverseList2(ListNode head) {
        return dfs(head,null);
    }
    //Todo:这样写递归更好理解，与双指针法一一对应
    public ListNode dfs(ListNode cur,ListNode pre){
        if(cur == null){
            return pre;
        }
        ListNode temp = cur.next;
        cur.next = pre;
        return dfs(temp,cur);
    }
}
