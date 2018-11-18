package code;

import org.junit.Test;
import properties.ListNode;

/**
 * Created by lanjing on 2018/11/14.
 */

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * <p>
 * <p>
 * For example, the following two linked lists:
 * <p>
 * A:          a1 → a2
 * ↘
 * c1 → c2 → c3
 * ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 * <p>
 * <p>
 * Notes:
 * <p>
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 * Credits:
 * Special thanks to @stellari for adding this problem and creating all test cases.
 * <p>
 * 要求：求两个相交链表的交点。可以认为链表没有环
 * <p>
 * 思路：先遍历判断两个链表有没有交点并记录每个链表长度，如果有交点，长链表先走两个长度差的步数，然后一起遍历，第一个相同的节点就是交点
 */
public class _160IntersectionOfTwoLinkedList {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }
        int lenA = 0, lenB = 0;
        ListNode p = headA;
        ListNode endA = null;
        while (p != null) {
            lenA++;
            if (p.next == null) {
                endA = p;
            }
            p = p.next;
        }
        p = headB;
        while (p != null) {
            lenB++;
            if (p.next == null) {
                if (endA != p) {
                    return null;
                }
            }
            p = p.next;
        }
        int diff = Math.abs(lenA - lenB);
        ListNode fast, slow;
        if (lenA > lenB) {
            fast = headA;
            slow = headB;
        } else {
            fast = headB;
            slow = headA;
        }
        while (diff > 0) {
            fast = fast.next;
            diff--;
        }
        while (fast != null) {
            if (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            } else {
                return fast;
            }
        }
        return null;
    }

    @Test
    public void run() {
        ListNode tail = new ListNode(3);
        tail.next(new ListNode(4)).next(new ListNode(5));
        ListNode l1 = new ListNode(4);
        l1.next(new ListNode(1)).next(new ListNode(2)).next(tail);
        ListNode l2 = new ListNode(2);
        l2.next(new ListNode(4)).next(tail);
        System.out.println(getIntersectionNode(l1, l2).val);
    }
}
