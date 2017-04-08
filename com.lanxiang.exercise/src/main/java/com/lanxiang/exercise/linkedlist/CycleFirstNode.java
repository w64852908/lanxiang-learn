package com.lanxiang.exercise.linkedlist;

/**
 * Created by lanjing on 2017/4/3.
 */

import org.junit.Test;

import java.util.Arrays;

/**
 * 已知一个单链表中存在环，求进入环中的第一个节点
 * <p>
 * 首先判断是否存在环，若不存在结束。在环中的一个节点处断开（当然函数结束时不能破坏原链表），
 * 这样就形成了两个相交的单链表，
 * 求进入环中的第一个节点也就转换成了求两个单链表相交的第一个节点
 */
public class CycleFirstNode {


    private boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode p = head;
        ListNode q = p.next;
        while (q != null && q.next != null) {
            if (p == q) {
                return true;
            }
            p = p.next;
            q = q.next.next;
        }
        return false;
    }

    @Test
    public void run() {
        ListNode head = LinkedListUtil.generate(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListNode n1 = new ListNode(10);
        ListNode n2 = new ListNode(100);
        ListNode n3 = new ListNode(1000);
        ListNode n4 = new ListNode(10000);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
    }
}
