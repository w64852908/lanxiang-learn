package com.lanxiang.exercise.linkedlist;

/**
 * Created by lanjing on 2017/4/5.
 */

import org.junit.Test;

import java.util.Arrays;

/**
 * 从链表倒数的位置删除节点
 */
public class RemoveNodeFromBack {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = head;

        ListNode pre = newHead;
        ListNode slow = head;
        ListNode fast = head;
        while (n > 0) {
            fast = fast.next;
            n--;
            if (fast == null) {
                return null;
            }
        }
        while (fast != null) {
            pre = pre.next;
            slow = slow.next;
            fast = fast.next;
        }
        pre.next = slow.next;
        slow.next = null;
        return newHead.next;
    }

    @Test
    public void run() {
        ListNode head = LinkedListUtil.generate(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        LinkedListUtil.show(head);
        removeNthFromEnd(head, 5);
        LinkedListUtil.show(head);
    }
}
