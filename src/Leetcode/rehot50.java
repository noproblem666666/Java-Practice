package Leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//环形链表
public class rehot50 {
    //快慢指针
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        //每次fast走两步，slow走一步
        while(fast!=null){
            fast = fast.next;
            //中间还要再检查一次是否为空
            if(fast==null){
                return false;
            }
            if(fast == slow){
                return true;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return false;
    }

    //哈希表检查
    public boolean hasCycle2(ListNode head) {
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
