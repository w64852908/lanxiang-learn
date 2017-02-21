package com.lanxiang.javaadvanced.concurrent;

import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by lanxiang on 2017/2/20.
 */
public class HashEntryTest {

    static class Node<E> {

        public E val;

        public Node next;

        public Node(E val) {
            this.val = val;
        }
    }

    static class LinkList<E> {

        private final E head;

        public LinkList(E head) {
            this.head = head;
        }
    }

    private void show(LinkList linkList) {
        Node node = (Node) linkList.head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = (Node) node.next;
        }
        System.out.println();
    }

    @Test
    public void run() {
        Node<String> node1 = new Node<>("b");
        Node<String> node2 = new Node<>("c");
        Node<String> node3 = new Node<>("d");
        node1.next = node2;
        node2.next = node3;
        LinkList<Node<String>> list = new LinkList<>(node1);
        show(list);
        Node<String> insertNode = new Node<>("a");
        node1.next = insertNode;
        insertNode.next = node2;
        String temp = node1.val;
        node1.val = insertNode.val;
        insertNode.val = temp;
        show(list);
    }
}
