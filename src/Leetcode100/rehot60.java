package Leetcode100;
//反转链表
public class rehot60 {
    //头插法
    public ListNode reverseList(ListNode head) {
        ListNode start = head;
        ListNode temp = new ListNode(0);
        while(head!=null){
            start = head.next;
            head.next = temp.next;
            temp.next = head;
            head = start;
        }
        return temp.next;
    }

    //递归解法，画图便于理解
    public ListNode reverseList2(ListNode head) {
        //递归终止条件是当前为空，或者下一个节点为空
        if(head==null || head.next==null) {
            return head;
        }
        //这里的cur就是最后一个节点
        ListNode cur = reverseList(head.next);
        //这里请配合动画演示理解
        //如果链表是 1->2->3->4->5，那么此时的cur就是5
        //而head是4，head的下一个是5，下下一个是空
        //所以head.next.next 就是5->4
        head.next.next = head;
        //防止链表循环，需要将head.next设置为空
        head.next = null;
        //每层递归函数都返回cur，也就是最后一个节点
        return cur;
    }

}
