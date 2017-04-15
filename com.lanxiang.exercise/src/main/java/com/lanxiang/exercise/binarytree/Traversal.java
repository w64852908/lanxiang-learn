package com.lanxiang.exercise.binarytree;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;

import java.util.*;

/**
 * Created by lanjing on 2017/3/28.
 */
public class Traversal {

    private static TreeNode root;

    /**
     * 中序,左根右
     */
    private List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode curr = stack.pop();
                result.add(curr.val);
                p = curr.right;
            }
        }
        return result;
    }

    /**
     * 前序,根左右
     */
    private List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                result.add(p.val);
                if (p.right != null) {
                    stack.push(p.right);
                }
                p = p.left;
            } else {
                p = stack.pop();
            }
        }
        return result;
    }

    /**
     * 后续,左右根
     */
    private List<Integer> nextOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        //存储逆序的结果
        Stack<TreeNode> output = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                output.push(p);
                stack.push(p);
                p = p.right;
            } else {
                p = stack.pop();
                p = p.left;
            }
        }
        while (output.size() > 0) {
            result.add(output.pop().val);
        }
        return result;
    }

    /**
     * 层次遍历
     */
    private List<Integer> levelTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode p;
        while (!queue.isEmpty()) {
            p = queue.poll();
            result.add(p.val);
            if (p.left != null) {
                queue.offer(p.left);
            }
            if (p.right != null) {
                queue.offer(p.right);
            }
        }
        return result;
    }

    /**
     * 分层的层次遍历
     */
    private List<List<Integer>> level2Traversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> curr = new LinkedList<>();
        curr.offer(root);
        Queue<TreeNode> next;
        while (!curr.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            next = new LinkedList<>();
            TreeNode p;
            while (!curr.isEmpty()) {
                p = curr.poll();
                level.add(p.val);
                if (p.left != null) {
                    next.offer(p.left);
                }
                if (p.right != null) {
                    next.offer(p.right);
                }
            }
            result.add(level);
            curr = next;
        }
        return result;
    }

    @Before
    public void init() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.right = node4;
        node4.left = node5;

        root = node1;
    }

    @Test
    public void inOrder() {
        System.out.println(inOrderTraversal(root));
    }

    @Test
    public void preOrder() {
        System.out.println(preOrderTraversal(root));
    }

    @Test
    public void nextOrder() {
        System.out.println(nextOrderTraversal(root));
    }

    @Test
    public void levelOrder() {
        System.out.println(levelTraversal(root));
    }

    @Test
    public void level2Order() {
        List<List<Integer>> result = level2Traversal(root);
        for (List<Integer> list : result) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
