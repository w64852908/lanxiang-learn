package code;

import org.junit.Test;
import properties.ListNode;
import properties.ListNodeUtil;

import java.util.List;

/**
 * Created by lanjing on 2018/10/29.
 */

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example 1:
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 * <p>
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 * <p>
 * 要求：给一个链表，按照上面的规则重新构建链表，要求不能改变节点的值
 * <p>
 * 思路：用快慢指针找到中间节点，前半部分链表为被插入链表，后半部分为插入链表，导致插入链表，然后合并两个链表即可
 */
public class _143ReorderList {

    public void reorderList(ListNode head) {
        if (null == head || null == head.next || null == head.next.next) {
            return;
        }
        ListNode slow = head, fast = head;
        //快慢指针，但是要快指针到最后一个节点即终止，慢指针才能在节点中间前一个
        while (null != fast.next) {
            slow = slow.next;
            fast = fast.next;
            if (null != fast.next) {
                fast = fast.next;
            } else {
                break;
            }
        }
        ListNode l1 = head;
        ListNode l2 = slow.next;
        slow.next = null;
        //倒置l2
        ListNode curr = l2.next;
        ListNode reverse = l2;
        reverse.next = null;
        while (null != curr) {
            ListNode p = curr;
            curr = curr.next;
            p.next = reverse;
            reverse = p;
        }
        l2 = reverse;

        ListNode newHead = new ListNode(-1);
        ListNode p = newHead;
        //合并两个链表
        while (null != l1 && null != l2) {
            p.next = l1;
            l1 = l1.next;
            p = p.next;
            p.next = l2;
            l2 = l2.next;
            p = p.next;
        }
        if (null == l1) {
            p.next = l2;
        } else {
            p.next = l1;
        }
        head = newHead.next;
    }

    @Test
    public void run() {
        ListNode l1 = new ListNode(1);
        l1.next(new ListNode(2)).next(new ListNode(3)).next(new ListNode(4)).next(new ListNode(5));

        ListNode reverse = null;
        ListNode curr = l1;
        while (curr != null) {
            ListNode p = curr;
            curr = curr.next;
            p.next = reverse;
            reverse = p;
        }
        l1 = reverse;
        ListNodeUtil.display(l1);


        System.out.println();
        reorderList(l1);
        ListNodeUtil.display(l1);
    }
}
