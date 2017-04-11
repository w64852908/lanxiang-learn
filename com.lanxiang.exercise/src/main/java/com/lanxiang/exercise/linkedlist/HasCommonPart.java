package com.lanxiang.exercise.linkedlist;

/**
 * Created by lanjing on 2017/4/3.
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 判断两个单链表是否相交
 * <p>
 * 如果两个链表相交于某一节点，那么在这个相交节点之后的所有节点都是两个链表所共有的。
 * 也就是说，如果两个链表相交，那么最后一个节点肯定是共有的
 */
public class HasCommonPart {

    public boolean hasCommon(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return false;
        }
        ListNode p1 = head1;
        while (p1.next != null) {
            p1 = p1.next;
        }
        ListNode last1 = p1;

        ListNode p2 = head2;
        while (p2.next != null) {
            p2 = p2.next;
        }
        return last1.equals(p2);
    }

    @Test
    public void run() {
        ListNode head1 = LinkedListUtil.generate(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListNode head2 = LinkedListUtil.generate(Arrays.asList(7, 8, 9, 10, 11, 12));
        ListNode tail1 = new ListNode(100);
        ListNode tail2 = new ListNode(1000);
        tail1.next = tail2;
        head1.next = tail1;
        head2.next = tail1;
        Assert.assertTrue(hasCommon(head1, head2));

        ListNode head3 = LinkedListUtil.generate(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListNode head4 = LinkedListUtil.generate(Arrays.asList(7, 8, 9, 10, 11, 12));
        Assert.assertTrue(hasCommon(head3, head4));
    }
}
