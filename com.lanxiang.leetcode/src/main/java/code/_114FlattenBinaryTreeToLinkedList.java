package code;

/**
 * Created by lanjing on 2018/10/16.
 */

import properties.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example, given the following tree:
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 * 要求：把一颗二叉树变成一个链表
 * <p>
 * 思路：前序遍历二叉树，然后修改每个树结点，空出左子树，右子树指向下一个节点
 */
public class _114FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        if (null == root) {
            return;
        }
        List<TreeNode> preOrderList = new ArrayList<>();
        preOrderGather(root, preOrderList);
        for (int i = 0; i < preOrderList.size() - 1; i++) {
            TreeNode curr = preOrderList.get(i);
            curr.left = null;
            curr.right = preOrderList.get(i + 1);
        }
    }

    private void preOrderGather(TreeNode root, List<TreeNode> preOrderList) {
        if (root == null) {
            return;
        }
        preOrderList.add(root);
        if (root.left != null) {
            preOrderGather(root.left, preOrderList);
        }
        if (root.right != null) {
            preOrderGather(root.right, preOrderList);
        }
    }

}
