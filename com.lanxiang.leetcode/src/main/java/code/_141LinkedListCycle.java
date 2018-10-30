package code;

/**
 * Created by lanjing on 2018/10/28.
 */

import properties.ListNode;

/**
 * Given a linked list, determine if it has a cycle in it.
 * <p>
 * Follow up:
 * Can you solve it without using extra space?
 * <p>
 * 要求：判断链表是否有环
 * <p>
 * 思路：快慢指针
 */
public class _141LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (null != fast && null != fast.next) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
