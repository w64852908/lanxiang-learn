package com.lanxiang.exercise.linkedlist;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lanjing on 2017/4/8.
 */

//从链表尾部翻转
public class ReverseFromTail {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        ListNode newHead = new ListNode(-1);
        ListNode preSlow = new ListNode(-1);
        ListNode preFast = new ListNode(-1);
        preSlow.next = head;
        preFast.next = head;
        while (k > 0) {
            if (preFast == null) {
                return null;
            }
            k--;
            preFast = preFast.next;
        }
        //临界条件,防止成为环
        if (preFast.next == null || preSlow == preFast) {
            return head;
        }
        while (preFast.next != null) {
            preSlow = preSlow.next;
            preFast = preFast.next;
        }
        newHead.next = preSlow.next;
        preSlow.next = null;
        preFast.next = head;
        return newHead.next;
    }

    @Test
    public void run() {
        ListNode head = LinkedListUtil.generate(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        int n = 10;
        head = rotateRight(head, n);
        LinkedListUtil.show(head);
    }
}
