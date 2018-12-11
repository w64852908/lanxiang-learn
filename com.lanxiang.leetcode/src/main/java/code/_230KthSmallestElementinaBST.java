package code;

import properties.TreeNode;

import java.util.Stack;

/**
 * Created by lanjing on 2018/12/10.
 */

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * 2
 * Output: 1
 * Example 2:
 * <p>
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * Output: 3
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 * <p>
 * 要求：找到二叉搜索树的第k小个元素
 * <p>
 * 思路：二叉搜索树使用中序遍历，找到第k个元素
 */
public class _230KthSmallestElementinaBST {

    public int kthSmallest(TreeNode root, int k) {
        if (null == root || k == 0) {
            return -1;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || null != p) {
            if (null != p) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                k--;
                if (k == 0) {
                    return p.val;
                }
                p = p.right;
            }
        }
        return -1;
    }
}
