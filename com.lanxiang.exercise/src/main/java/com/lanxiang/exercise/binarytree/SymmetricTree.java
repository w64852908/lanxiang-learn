package com.lanxiang.exercise.binarytree;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/9.
 */
public class SymmetricTree {

    public boolean isSymmetricTree(TreeNode p) {
        if (p == null) {
            return true;
        }
        return judge(p.left, p.right);
    }

    private boolean judge(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return judge(p.left, q.right) && judge(p.right, q.left);
    }

    @Test
    public void run() {
        TreeNode root = buildTree();
        System.out.println(isSymmetricTree(root));
    }

    private TreeNode buildTree() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        return t1;
    }
}
