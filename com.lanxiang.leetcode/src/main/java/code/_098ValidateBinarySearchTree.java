package code;

/**
 * Created by lanjing on 2017/3/28.
 */

import io.swagger.models.auth.In;
import properties.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * 2
 * / \
 * 1   3
 * Binary tree [2,1,3], return true.
 * Example 2:
 * 1
 * / \
 * 2   3
 * Binary tree [1,2,3], return false.
 * <p>
 * 要求：判断一棵二叉树是不是平衡二叉搜索树
 * <p>
 * 思路：中序遍历平衡二叉搜索树的话，值一直会是递增的
 */
public class _098ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        List<Integer> inOrderList = new ArrayList<>();
        inOrderGather(root, inOrderList);
        for (int i = 0; i < inOrderList.size() - 1; i++) {
            if (inOrderList.get(i) >= inOrderList.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    //中序遍历
    private void inOrderGather(TreeNode root, List<Integer> inOrderList) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrderGather(root.left, inOrderList);
        }
        inOrderList.add(root.val);
        if (root.right != null) {
            inOrderGather(root.right, inOrderList);
        }
    }
}
