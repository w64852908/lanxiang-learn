package code;

import properties.ListNode;

/**
 * Created by lanjing on 2018/12/11.
 */

/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 * <p>
 * Given linked list -- head = [4,5,1,9], which looks like following:
 * <p>
 * 4 -> 5 -> 1 -> 9
 * Example 1:
 * <p>
 * Input: head = [4,5,1,9], node = 5
 * Output: [4,1,9]
 * Explanation: You are given the second node with value 5, the linked list
 * should become 4 -> 1 -> 9 after calling your function.
 * Example 2:
 * <p>
 * Input: head = [4,5,1,9], node = 1
 * Output: [4,5,9]
 * Explanation: You are given the third node with value 1, the linked list
 * should become 4 -> 5 -> 9 after calling your function.
 * Note:
 * <p>
 * The linked list will have at least two elements.
 * All of the nodes' values will be unique.
 * The given node will not be the tail and it will always be a valid node of the linked list.
 * Do not return anything from your function.
 * <p>
 * 要求：给一个链表中的一个节点，删除这个节点
 * <p>
 * 思路：因为不知道这个链表的上一个节点，所以只能删除这个节点的下一个节点，然后把当前节点的值赋到下一个节点
 */
public class _237DeleteNodeInALinkedList {

    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        int temp = next.val;
        node.next = next.next;
        next.next = null;
        node.val = temp;
    }
}
