package com.arts.LeetCode;

import com.arts.LeetCode.common.ListNode;

/**
 * 题目：两个非负整数的非空链表，数字以相反的方向存储，每个节点包含一个数字，两数相加 以链表形式返回结果。
 * 例：Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *    Output: 7 -> 0 -> 8
 */
public class AddTwoNumber {

    public ListNode addTwoNumber(ListNode l1, ListNode l2) {
        ListNode resultHead = new ListNode(0);
        ListNode q = l1, p = l2, curr = resultHead;
        int carry = 0; //进位
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry = sum/10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            p = (p != null) ? p.next : null;
            q = (q != null) ? q.next : null;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return resultHead.next;
    }

    public static void main(String[] args){
        AddTwoNumber addTwoNumber = new AddTwoNumber();
        ListNode l1 = new ListNode(2);
        ListNode l11 = new ListNode(4);
        ListNode l12 = new ListNode(3);
        l1.next = l11;
        l1.next.next = l12;
        ListNode l2 = new ListNode(5);
        ListNode l21 = new ListNode(6);
        ListNode l22 = new ListNode(8);
        l2.next = l21;
        l2.next.next = l22;

        ListNode resLinkNode = addTwoNumber.addTwoNumber(l1, l2);
        ListNode l = resLinkNode;
        while (l != null) {
            System.out.println(l.val);
            l = l.next;
        }


    }

}
