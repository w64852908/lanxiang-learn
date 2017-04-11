package com.lanxiang.exercise.linkedlist;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lanjing on 2017/4/8.
 */
public class RemoveDuplicateNode {

    public ListNode removeDuplicate(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            if (pre.val == curr.val) {
                curr.next = null;
                curr = next;
                pre.next = curr;
            } else {
                curr = curr.next;
                pre = pre.next;
            }
        }
        return head;
    }

    @Test
    public void run() {
        ListNode head = LinkedListUtil.generate(Arrays.asList(1, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 7));
        LinkedListUtil.show(head);
        removeDuplicate(head);
        LinkedListUtil.show(head);
    }
}
