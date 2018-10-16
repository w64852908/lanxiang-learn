package code;

import org.junit.Test;
import properties.ListNode;
import properties.ListNodeUtil;

/**
 * Created by lanjing on 2018/10/14.
 */

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 * <p>
 * 要求：链表部分节点倒置
 * <p>
 * 思路
 * 我们可以先取出2，用front指针指向2，然后当取出3的时候，我们把3加到2的前面，把front指针前移到3，依次类推，到4后停止，
 * 这样我们得到一个新链表4->3->2, front指针指向4。对于原链表连说，有两个点的位置很重要，需要用指针记录下来，分别是1和5，
 * 因为当2,3,4被取走时，原链表就变成了1->5->NULL，要把新链表插入的时候需要这两个点的位置。1的位置很好找，因为知道m的值，
 * 我们用pre指针记录1的位置，5的位置最后才能记录，当4结点被取走后，5的位置需要记下来，这样我们就可以把倒置后的那一小段链表加入到原链表中
 */
public class _092ReverseLinkedList2 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (null == head) {
            return null;
        }
        ListNode nullHead = new ListNode(-1);
        nullHead.next = head;
        ListNode curr = nullHead;
        ListNode pre, last;
        int index = 1;
        //走到m前一个位置
        while (index <= m - 1) {
            curr = curr.next;
            index++;
        }

        pre = curr;
        last = curr.next;
        ListNode front = null;

        //这里的while循环，和普通的单链表倒置思路一致，只是pre前置节点要固定不变
        while (m <= n) {
            curr = pre.next;
            pre.next = curr.next;
            curr.next = front;
            front = curr;
            m++;
        }
        curr = pre.next;
        pre.next = front;
        last.next = curr;
        return nullHead.next;
    }

    @Test
    public void run() {
        ListNode head = new ListNode(1);
        head.next(new ListNode(3)).next(new ListNode(5)).next(new ListNode(7))
                .next(new ListNode(9));
        ListNodeUtil.display(head);
        System.out.println();
        reverseBetween(head, 2, 4);
        ListNodeUtil.display(head);
    }
}
