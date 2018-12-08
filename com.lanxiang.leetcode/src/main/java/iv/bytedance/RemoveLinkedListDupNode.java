package iv.bytedance;

import org.junit.Test;
import properties.ListNode;
import properties.ListNodeUtil;

import java.util.Arrays;

/**
 * Created by lanjing on 2018/12/8.
 */

/**
 * 删除链表的重复节点
 * <p>
 * 思路：
 * 1.给链表加一个前置节点，防止1和2两个节点相同的情况
 * 2.同时操作pre、curr和next三个节点
 * 3.用一个dupValue来表示是否有重复元素，并保留这个重复元素
 * 4.发现重复节点时，给dupValue赋值，发现不重复节点时，操作链表，然后给dupValue置为null
 */
public class RemoveLinkedListDupNode {

    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        ListNode curr = head;
        ListNode pre = new ListNode(-1);
        pre.next = head;
        head = pre;

        ListNode next = curr.next;
        Integer dupValue = null;
        while (null != next) {
            //不存在重复元素
            if (null == dupValue) {
                //记录重复元素，然后移动next和curr
                if (curr.val == next.val) {
                    dupValue = curr.val;
                    next = next.next;
                    curr = curr.next;
                } else {
                    //没有重复元素，移动三个指针
                    pre = pre.next;
                    curr = curr.next;
                    next = next.next;
                }
            } else {
                //存在重复元素，且当前元素和下一个元素还是重复的
                if (curr.val == next.val) {
                    next = next.next;
                    curr = curr.next;
                } else {
                    //终于不重复了，操作指针，然后dupValue置为null
                    dupValue = null;
                    pre.next = next;
                    curr = next;
                    next = next.next;
                }
            }
        }
        //这里还有可能存在整个链表都是重复元素的情况
        if (dupValue != null) {
            pre.next = null;
        }
        return head.next;
    }

    @Test
    public void run() {
        ListNode head = ListNodeUtil.generateList(Arrays.asList(1, 1, 2));
        ListNodeUtil.display(head);
        System.out.println();
        ListNodeUtil.display(deleteDuplicates(head));
    }
}
