/**
 * Created by lanjing on 2017/2/14.
 */

import org.junit.Test;
import properties.ListNode;
import properties.ListNodeUtil;

import java.util.Arrays;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * <p>
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 * <p>
 * 题意:给一个有序链表,删除所有有重复值的节点
 */
public class _082RemoveDuplicatesFromSortedList2 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head;
        ListNode pre = new ListNode(-1);
        pre.next = head;
        //先把head节点指向头节点前一个
        head = pre;
        ListNode next = curr.next;
        Integer sameValue = null;
        while (next != null) {
            //如果没有重复的值的记录
            if (sameValue == null) {
                //如果当前节点与下一个节点值相同,记录下当前重复值,删除后面的节点
                if (curr.val == next.val) {
                    sameValue = curr.val;
                    next = next.next;
                    curr.next = next;
                    pre.next = curr;
                } else {
                    //正常情况,继续
                    pre = pre.next;
                    curr = curr.next;
                    next = next.next;
                }
            } else {
                //如果上次重复的值与下一个节点的值又重复了,继续删除后面的节点
                if (sameValue == next.val) {
                    next = next.next;
                    curr.next = next;
                    pre.next = curr;
                } else {
                    //如果没有重复,则删除前一个值,并且把记录的重复值置为null
                    sameValue = null;
                    curr = next;
                    next = next.next;
                    pre.next = curr;

                }
            }
            //如果删除的相同节点是最后一个节点的话
            if (next == null && sameValue != null) {
                pre.next = null;
                break;
            }
        }
        return head.next;
    }

    @Test
    public void run() {
//        ListNode head = ListNodeUtil.generateList(Arrays.asList(1, 2, 3, 3, 4, 4, 5));
        ListNode head = ListNodeUtil.generateList(Arrays.asList(1, 1));
        _082RemoveDuplicatesFromSortedList2 main = new _082RemoveDuplicatesFromSortedList2();
        head = main.deleteDuplicates(head);
        ListNodeUtil.display(head);
    }
}
