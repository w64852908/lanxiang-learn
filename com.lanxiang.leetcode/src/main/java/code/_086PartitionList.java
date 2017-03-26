package code; /**
 * Created by lanjing on 2017/2/15.
 */

import org.junit.Test;
import properties.ListNode;
import properties.ListNodeUtil;

import java.util.Arrays;


/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * <p>
 * 题意:给定一个单链表和一个x，把链表中小于x的放到前面，大于等于x的放到后面，每部分元素的原始相对位置不变。
 */
public class _086PartitionList {

    public ListNode partition(ListNode head, int x) {
        //比x大的链表
        ListNode gHead = new ListNode(0);
        //比x小的链表
        ListNode lHead = new ListNode(0);
        ListNode p = head;
        //大链表的游标
        ListNode greatter = gHead;
        ListNode less = lHead;
        while (p != null) {
            ListNode next = p.next;
            //挂载小链表上
            if (p.val < x) {
                less.next = p;
                less = less.next;
                p.next = null;
            } else {
                greatter.next = p;
                greatter = greatter.next;
                p.next = null;
            }
            p = next;
        }
        //小链表的尾部接上大链表的头部
        less.next = gHead.next;
        return lHead.next;
    }

    @Test
    public void run() {
        ListNode head = ListNodeUtil.generateList(Arrays.asList(-98,-22,5,-31,-92,-42));
        head = partition(head, -57);
        ListNodeUtil.display(head);
    }
}
