package com.lanxiang.exercise.linkedlist;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lanjing on 2017/3/28.
 */
public class MergeLinkedList {

    public Node merge(Node p1, Node p2) {
        Node newHead = new Node(-1);
        Node p = newHead;
        Node q;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                q = p2.next;
                p.next = p2;
                p2.next = null;
                p2 = q;
            } else {
                q = p1.next;
                p.next = p1;
                p1.next = null;
                p1 = q;
            }
            p = p.next;
        }
        if (p1 == null) {
            p.next = p2;
        } else {
            p.next = p1;
        }
        return newHead.next;
    }

    @Test
    public void run() {
        Node p1 = LinkedListUtil.generate(Arrays.asList(1, 3, 5, 7, 9));
        Node p2 = LinkedListUtil.generate(Arrays.asList(2, 4, 6, 8, 10));
        Node p = merge(p1, p2);
        LinkedListUtil.show(p);
    }
}
