package com.lanxiang.exercise.binarytree;

/**
 * Created by lanjing on 2017/4/9.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回所有根节点到叶子节点的路径
 */
public class BinaryTreePath {

    public List<String> binaryPaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        byRecuisive(root, new StringBuilder(), result);
        return result;
    }

    private void byRecuisive(TreeNode p, StringBuilder temp, List<String> result) {
        temp.append(p.val);
        if (p.left == null && p.right == null) {
            result.add(temp.toString());
            return;
        }
        if (p.left != null) {
            byRecuisive(p.left, temp, result);
            temp.deleteCharAt(temp.length() - 1);
        }
        if (p.right != null) {
            byRecuisive(p.right, temp, result);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    @Test
    public void run() {
        TreeNode root = buildTree();
        System.out.println(binaryPaths(root));
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
