package com.lanxiang.exercise.linkedlist;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lanjing on 2017/4/8.
 */
public class RemoveAllDuplicateNode {

    public ListNode removeDuplicate(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode pre = newHead;
        ListNode curr = head;
        ListNode next;
        Integer dup = null;
        while (curr != null && curr.next != null) {
            next = curr.next;
            if (dup == null) {
                if (curr.val == next.val) {
                    dup = curr.val;
                    curr.next = null;
                    curr = next.next;
                    next.next = null;
                    pre.next = null;
                    pre.next = curr;
                } else {
                    pre = pre.next;
                    curr = curr.next;
                }
            } else {
                if (curr.val == dup) {
                    curr.next = null;
                    curr = next;
                    pre.next = curr;
                } else {
                    dup = null;
                }
            }
        }
        if (dup != null && (pre.next == null || dup == pre.next.val)) {
            pre.next = null;
        }
        return newHead.next;
    }

    @Test
    public void run() {
//        ListNode head = LinkedListUtil.generate(Arrays.asList(1, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 7));
        ListNode head = LinkedListUtil.generate(Arrays.asList(1, 1, 2));
        head = removeDuplicate(head);
        LinkedListUtil.show(head);
    }

}
