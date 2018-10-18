package code;

/**
 * Created by lanjing on 2018/10/18.
 */

import org.junit.Test;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any
 * node in the list or null.
 * <p>
 * Return a deep copy of the list.
 * <p>
 * 要求：一个链表，每个节点都有一个随机指向其他节点或者null的指针，深拷贝该链表
 * <p>
 * 思路：3次循环
 * 1.遍历链表，在每一个节点后面创建一个同样的节点，并把该新节点放在当前节点后面
 * 2.新节点的random指针指向老节点的random指针的next节点
 * 3.分割包含重复节点的链表
 */
public class _138CopyListwithRandomPointer {

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode p = head;
        while (p != null) {
            RandomListNode newNode = new RandomListNode(p.label);
            RandomListNode next = p.next;
            p.next = newNode;
            newNode.next = next;
            p = p.next.next;
        }
        p = head;
        while (p != null) {
            if (p.random != null) {
                RandomListNode copyNode = p.next;
                copyNode.random = p.random.next;
            }
            p = p.next.next;
        }
        p = head;
        RandomListNode newNode = p.next;
        while (p.next != null) {
            RandomListNode next = p.next;
            p.next = p.next.next;
            p = next;
        }
        return newNode;
    }

    @Test
    public void run() {
        RandomListNode l1 = new RandomListNode(1);

        RandomListNode l2 = new RandomListNode(2);

        RandomListNode l3 = new RandomListNode(3);

        RandomListNode l4 = new RandomListNode(4);

        RandomListNode l5 = new RandomListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        l1.random = l3;
        l3.random = l5;
        l5.random = l2;

        copyRandomList(l1);
    }

    private void show(RandomListNode head) {
        while (head != null) {
            System.out.print(head.label + "->");
            head = head.next;
        }
    }
}

class RandomListNode {

    int label;

    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
}
