package code;

import org.junit.Test;

import properties.ListNode;
import properties.ListNodeUtil;

/**
 * Created by lanxiang on 2018/12/1.
 */

/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2
 * Output: false
 * Example 2:
 * <p>
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 * <p>
 * 要求：输入一个链表，判断链表是否是回文的，要时间复杂度O(n),O(1)空间复杂度
 * <p>
 * 思路：先用快慢指针找到中间点，把后半部分链表倒置，然后同时遍历两个链表，判断是否回文
 */
public class _234PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (null == head) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p = slow.next;
        ListNode reverse = slow;
        reverse.next = null;
        while (null != p) {
            ListNode curr = p;
            p = p.next;
            curr.next = reverse;
            reverse = curr;
        }
        while (null != head && null != reverse) {
            if (head.val != reverse.val) {
                return false;
            }
            head = head.next;
            reverse = reverse.next;
        }
        return true;
    }

    @Test
    public void run() {
        ListNode head = new ListNode(1);
        head.next(new ListNode(3)).next(new ListNode(5)).next(new ListNode(3))
                .next(new ListNode(1));
        ListNodeUtil.display(head);
        System.out.println();
        System.out.println(isPalindrome(head));
    }
}
