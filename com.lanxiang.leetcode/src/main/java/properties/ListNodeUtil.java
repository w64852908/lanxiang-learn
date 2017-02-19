package properties;

import java.util.List;

/**
 * Created by lanjing on 2017/2/14.
 */
public class ListNodeUtil {

    public static ListNode generateList(List<Integer> list) {
        if (list == null || list.size() < 1) {
            return null;
        }
        ListNode head = new ListNode(list.get(0));
        ListNode p = head;
        for (int i = 1; i < list.size(); i++) {
            p.next = new ListNode(list.get(i));
            p = p.next;
        }
        return head;
    }

    public static void display(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
