package com.lanxiang.exercise.binarytree;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/9.
 */
public class SameTree {

    public boolean isSameTree(TreeNode p1, TreeNode p2) {
        if (p1 == null && p2 == null) {
            return true;
        } else if (p1 == null || p2 == null) {
            return false;
        }
        if (p1.val != p2.val) {
            return false;
        }
        return isSameTree(p1.left, p2.left) && isSameTree(p1.right, p2.right);
    }

    @Test
    public void run() {
        TreeNode root1 = buildTree();
        TreeNode root2 = buildTree();
        System.out.println(isSameTree(root1, root2));
    }

    private TreeNode buildTree() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t5.left = t7;
        t3.left = t6;
        return t1;
    }
}
