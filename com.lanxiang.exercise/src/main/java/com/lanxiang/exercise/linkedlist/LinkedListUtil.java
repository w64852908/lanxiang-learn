package com.lanxiang.exercise.linkedlist;

import java.util.List;

/**
 * Created by lanjing on 2017/3/27.
 */
public class LinkedListUtil {

    public static Node generate(List<Integer> arr) {
        if (arr == null || arr.size() == 0) {
            return new Node(-1);
        }
        Node preHead = new Node(-1);
        Node p = preHead;
        for (Integer anArr : arr) {
            p.next = new Node(anArr);
            p = p.next;
        }
        return preHead.next;
    }

    public static void show(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
