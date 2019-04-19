package com.arts.LeetCode;

public class Reverse {

    //翻转整数
    public int reverse(int x) {
        int rev = 0;
        while (x !=0){
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE/10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE/10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    //反转链表
    public ListNode reverseList(ListNode head) {
        ListNode reverseNode = null;
        ListNode pNode = head;
        ListNode prev = null;
        while (pNode != null) {
            ListNode pNext = pNode.next;
            if (pNext == null) {
                reverseNode = pNode;
            }
            pNode.next = prev;
            prev = pNode;
            pNode = pNext;
        }
        return reverseNode;
    }

    //两两交换链表
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        head = head.next;
        ListNode curRight = null;
        ListNode nextCur = null;
        while (cur != null && cur.next != null) {
            curRight = cur.next;
            nextCur = curRight.next;
            curRight.next = cur;
            cur.next = nextCur;
            if (nextCur != null && nextCur.next != null) {
                cur.next = nextCur.next;
            }
            cur = nextCur;
        }
        return head;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        Reverse reverse = new Reverse();
        int x = -100;
        System.out.println(reverse.reverse(x));


        LinkedList linkedList = new LinkedList();
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.addNode(6);

        //ListNode reverseList = reverse.reverseList(linkedList.head);
        //System.out.println(reverseList);

        System.out.println(reverse.hasCycle(linkedList.head));


    }
}
