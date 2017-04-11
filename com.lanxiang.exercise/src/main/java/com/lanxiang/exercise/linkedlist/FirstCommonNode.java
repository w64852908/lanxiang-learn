package com.lanxiang.exercise.linkedlist;

/**
 * Created by lanjing on 2017/4/3.
 */


import org.junit.Test;

import java.util.Arrays;

/**
 * 求两个单链表相交的第一个节点
 * <p>
 * 对第一个链表遍历，计算长度len1，同时保存最后一个节点的地址。
 * 对第二个链表遍历，计算长度len2，同时检查最后一个节点是否和第一个链表的最后一个节点相同，若不相同，不相交，结束。
 * 两个链表均从头节点开始，假设len1大于len2，那么将第一个链表先遍历len1-len2个节点，此时两个链表当前节点到第一个相交节点的距离就相等了，
 * 然后一起向后遍历，知道两个节点的地址相同。
 */
public class FirstCommonNode {

    public ListNode getFirstCommonNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        int len1 = 0;
        ListNode p1 = head1;
        while (p1.next != null) {
            p1 = p1.next;
            len1++;
        }
        ListNode last1 = p1;

        int len2 = 0;
        ListNode p2 = head2;
        while (p2.next != null) {
            p2 = p2.next;
            len2++;
        }
        if (!last1.equals(p2)) {
            return null;
        } else {
            int distance = len1 > len2 ? len1 - len2 : len2 - len1;
            if (len1 > len2) {
                moveForward(head1, distance);
            } else {
                moveForward(head2, distance);
            }
            ListNode curr1, curr2;
            while (head1 != null) {
                curr1 = head1.next;
                curr2 = head2.next;
                if (curr1 == curr2) {
                    return curr1;
                } else {
                    head1 = head1.next;
                    head2 = head2.next;
                }
            }
            return null;
        }
    }

    private void moveForward(ListNode p, int i) {
        if (i <= 0) {
            return;
        }
        while (i > 0) {
            p = p.next;
            i--;
        }
    }

    @Test
    public void run() {
        ListNode head1 = LinkedListUtil.generate(Arrays.asList(1, 2, 3));
        ListNode head2 = LinkedListUtil.generate(Arrays.asList(7, 8, 9, 10, 11, 12));
        ListNode tail1 = new ListNode(100);
        ListNode tail2 = new ListNode(1000);
        tail1.next = tail2;
        head1.next = tail1;
        head2.next = tail1;
        System.out.println(getFirstCommonNode(head1, head2).val);
    }
}
