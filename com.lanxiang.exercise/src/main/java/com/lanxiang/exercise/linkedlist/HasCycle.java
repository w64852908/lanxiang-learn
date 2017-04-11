package com.lanxiang.exercise.linkedlist;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lanjing on 2017/4/3.
 */

/**
 * 判断链表中是否存在环
 * <p>
 * 这里也是用到两个指针。如果一个链表中有环，也就是说用一个指针去遍历，是永远走不到头的。
 * 因此，我们可以用两个指针去遍历，一个指针一次走两步，一个指针一次走一步，如果有环，两个指针肯定会在环中相遇。时间复杂度为O（n）
 */


public class HasCycle {

    public boolean hasCycle(ListNode head) {
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
        Assert.assertTrue(hasCycle(buildCycle()));
        Assert.assertTrue(hasCycle(buildLinkedList()));

    }

    private ListNode buildCycle() {
        ListNode head = LinkedListUtil.generate(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListNode tail = new ListNode(7);
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = tail;
        tail.next = head;
        return head;
    }

    private ListNode buildLinkedList() {
        return LinkedListUtil.generate(Arrays.asList(1, 2, 3, 4, 5, 6));
    }
}
