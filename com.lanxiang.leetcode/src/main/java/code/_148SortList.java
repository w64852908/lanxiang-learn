package code;

import org.junit.Test;
import properties.ListNode;
import properties.ListNodeUtil;

/**
 * Created by lanjing on 2018/11/7.
 */

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 * <p>
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 * <p>
 * 要求：对一个链表排序，要求时间复杂度O(nlogn)，不申请额外空间
 * <p>
 * 思路：对一个链表进行归并排序。
 * 知识点1：归并排序的整体思想
 * 知识点2：找到一个链表的中间节点的方法
 * 知识点3：合并两个已排好序的链表为一个新的有序链表
 */
public class _148SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = getMiddleOfList(head);
        ListNode next = middle.next;
        middle.next = null;
        return mergeList(sortList(head), sortList(next));
    }

    //快慢指针找到链表中间节点
    private ListNode getMiddleOfList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //合并两个有序链表
    ListNode mergeList(ListNode a, ListNode b) {
        ListNode dummyHead = new ListNode(-1);
        ListNode curr = dummyHead;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = a != null ? a : b;
        return dummyHead.next;
    }

    @Test
    public void run() {
        ListNode l1 = new ListNode(4);
        l1.next(new ListNode(2)).next(new ListNode(1)).next(new ListNode(3)).next(new ListNode(100));
        l1 = sortList(l1);
        ListNodeUtil.display(l1);
    }
}
