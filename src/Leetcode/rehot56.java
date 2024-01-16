package Leetcode;

import java.util.HashSet;

//相交链表（快慢指针或哈希表）
public class rehot56 {
    //哈希表检查是否存在，若存在，返回的第一个相同结点即是相交的结点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        while(headA!=null){
            set.add(headA);
            headA = headA.next;
        }
        while(headB!=null){
            if(set.contains(headB)){
                return headB;
            }
            headB=headB.next;
        }
        return null;
    }

    //快慢指针
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode countA = headA,countB = headB;
        while(countA!=null&&countB!=null){
            countA = countA.next;
            countB = countB.next;
        }
        //通过长度差值，让长的链表先走
        while(countA!=null){
            headA=headA.next;
            countA = countA.next;
        }
        while(countB!=null){
            headB=headB.next;
            countB = countB.next;
        }
        while(headA!=null&&headB!=null){
            if(headA==headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    //Todo：非常巧妙的写法，因为这样两个指针都走过了相同的距离
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


}
