package code;

import org.junit.Test;
import properties.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by lanjing on 2017/4/18.
 */

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 *
 * 要求:Z字形层次遍历二叉树
 *
 * 思路:偶数层进行一次reverse操作即可
 */
public class _103BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> curr = new LinkedList<>();
        Queue<TreeNode> next;
        curr.offer(root);
        int level = 1;
        TreeNode p;
        while (!curr.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            next = new LinkedList<>();
            while (!curr.isEmpty()) {
                p = curr.poll();
                temp.add(p.val);
                if (p.left != null) {
                    next.offer(p.left);
                }
                if (p.right != null) {
                    next.offer(p.right);
                }
            }
            boolean asc = (level % 2 == 0);
            if (asc) {
                temp = reverseList(temp);
            }
            result.add(temp);
            if (!next.isEmpty()) {
                curr = next;
                level++;
            }
        }
        return result;
    }

    private <T> List<T> reverseList(List<T> list) {
        List<T> result = new ArrayList<>(list.size());
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    @Test
    public void run() {
        System.out.println(zigzagLevelOrder(case1()));
        System.out.println(zigzagLevelOrder(case2()));
    }

    private TreeNode case1() {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        return t1;
    }

    private TreeNode case2() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.right = t5;
        return t1;
    }
}
