package com.lanxiang.exercise.linkedlist;

import java.util.List;

/**
 * Created by lanjing on 2017/3/27.
 */
public class LinkedListUtil {

    public static ListNode generate(List<Integer> arr) {
        if (arr == null || arr.size() == 0) {
            return new ListNode(-1);
        }
        ListNode preHead = new ListNode(-1);
        ListNode p = preHead;
        for (Integer anArr : arr) {
            p.next = new ListNode(anArr);
            p = p.next;
        }
        return preHead.next;
    }

    public static void show(ListNode head) {
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.val + "->");
            } else {
                System.out.println(head.val);
            }
            head = head.next;
        }
        System.out.println();
    }
}
