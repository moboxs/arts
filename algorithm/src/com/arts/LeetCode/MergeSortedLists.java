package com.arts.LeetCode;

//合并两个有序链表，合并后的链表也是顺序的
public class MergeSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2;
        ListNode resultNode = new ListNode(0);
        ListNode curNode = resultNode;
        while (p != null || q != null) {
            if (p != null && q != null) {
                if (p.data <= q.data ) {
                    curNode.next = new ListNode(p.data);
                    p = p.next;
                } else {
                    curNode.next = new ListNode(q.data);
                    q = q.next;
                }
            } else if (p != null) {
                curNode.next = p;
                p = null;
            } else if (q != null) {
                curNode.next = q;
                q = null;
            }
            curNode = curNode.next;
        }
        return resultNode.next;
    }

    //合并K个链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        int len = lists.length;
        if (len == 1) {
            return lists[0];
        }
        if (len == 2) {
            return mergeTwoLists(lists[0], lists[1]);
        }

        int mid = len/2;
        ListNode[] l1 = new ListNode[mid];
        for (int i=0; i<mid; i++) {
            l1[i] = lists[i];
        }
        ListNode[] l2 = new ListNode[len-mid];
        for (int i=mid,j=0; i<len; i++,j++) {
            l2[j] = lists[i];
        }

        return mergeTwoLists(mergeKLists(l1), mergeKLists(l2));
    }
    
    public static void main(String[] args){
        LinkedList linkedList = new LinkedList();
        linkedList.addNode(1);
        linkedList.addNode(3);
        linkedList.addNode(3);
        linkedList.addNode(5);

        LinkedList linkedList1 = new LinkedList();
        linkedList1.addNode(1);
        linkedList1.addNode(2);
        linkedList1.addNode(4);
        linkedList1.addNode(4);
        linkedList1.addNode(6);

        MergeSortedLists mergeTwoSortedLists = new MergeSortedLists();
        ListNode resNode = mergeTwoSortedLists.mergeTwoLists(linkedList.head, linkedList1.head);
        System.out.println(resNode);
    } 
}
