package com.lanxiang.exercise.linkedlist;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lanjing on 2017/3/27.
 */
public class Reverse {

    public Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = new Node(-1);
        Node p = head;
        Node q;
        while (p != null) {
            q = p.next;
            p.next = newHead.next;
            newHead.next = p;
            p = q;
        }
        return newHead.next;
    }

    @Test
    public void run() {
        Node head = LinkedListUtil.generate(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        LinkedListUtil.show(head);
        head = reverse(head);
        LinkedListUtil.show(head);
    }
}
