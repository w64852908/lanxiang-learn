package code;

import org.junit.Test;
import properties.ListNode;
import properties.ListNodeUtil;

/**
 * Created by lanjing on 2018/11/6.
 */

/**
 * Sort a linked list using insertion sort.
 * <p>
 * <p>
 * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
 * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 * <p>
 * <p>
 * Algorithm of Insertion Sort:
 * <p>
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
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
 * 要求：用插入排序的方式排序链表
 * <p>
 * 思路：创建一个新的链表，把遍历原链表每一个节点，按大小插入心里暗标
 */
public class _147InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode newHead = new ListNode(-1);
        ListNode p = head;
        while (null != p) {
            //记录当前节点的下一个节点，因为当前节点插入新链表之后会去掉next的引用
            ListNode next = p.next;
            insertNode(newHead, p);
            p = next;
        }
        return newHead.next;
    }

    private void insertNode(ListNode newHead, ListNode insert) {
        ListNode head = newHead;
        ListNode curr;
        ListNode next;
        while (null != head) {
            curr = head;
            next = curr.next;
            if (null == next) {
                curr.next = insert;
                //处理插入节点的下一个节点的引用
                insert.next = null;
                break;
            } else {
                if (next.val >= insert.val) {
                    curr.next = insert;
                    insert.next = next;
                    break;
                }
            }
            head = head.next;
        }
    }

    @Test
    public void run() {
        ListNode l1 = new ListNode(4);
        l1.next(new ListNode(2)).next(new ListNode(1)).next(new ListNode(3));
        ListNodeUtil.display(l1);
        System.out.println();
        l1 = insertionSortList(l1);
        ListNodeUtil.display(l1);
    }
}
