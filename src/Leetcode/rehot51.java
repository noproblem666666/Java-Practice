package Leetcode;
//环形链表（二）
public class rehot51 {
    //快慢指针，先计算环的长度，再用快慢指针找环入口
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        //统计环的长度（其实原理和下面方法是一样的，这里计算的也可能是多次环的长度）
        //因为只要有环，长度至少为1，因此count初始值应该为1
        int count = 1;
        while(fast!=null){
            if(fast==slow){
                break;
            }
            if(fast.next==null){
                return null;
            }
            fast= fast.next.next;
            slow = slow.next;
            count++;
        }

        if(fast==null){
            return null;
        }
        fast = head;
        slow = head;
        //快指针先走环长度的步数，找倒数第k个节点
        while(count>0){
            fast=fast.next;
            count--;
        }
        while(fast!=slow){
            fast=fast.next;
            slow = slow.next;
        }
        return fast;
    }

    //不计算环长度，遇到后fast直接从head开始和slow一起走，直到再次相遇
    public ListNode detectCycle2(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }


}
