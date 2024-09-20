package Leetcode150.LinkedList;

import java.util.HashSet;
import java.util.Set;

//环形链表
public class leetcode141 {
    //快慢指针（每次快指针走两步，慢指针走一步）
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null){
            fast = fast.next;

            if(fast == slow){
                return true;
            }
            if(fast==null){
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return false;
    }

    //另一种写法
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    //哈希表法
    public boolean hasCycle3(ListNode head) {
        Set<ListNode> seen = new HashSet<ListNode>();
        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

}
