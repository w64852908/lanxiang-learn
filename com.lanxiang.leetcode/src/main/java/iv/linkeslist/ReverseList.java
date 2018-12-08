package iv.linkeslist;

import properties.ListNode;

/**
 * Created by lanjing on 2018/12/7.
 */
public class ReverseList {

    public ListNode reverse(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode p = head.next;
        ListNode reverse = head;
        reverse.next = null;
        while (p != null) {
            ListNode next = p.next;
            p.next = reverse;
            reverse = p;
            p = next;
        }
        return reverse;
    }
}
