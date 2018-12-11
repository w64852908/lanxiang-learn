package code;

/**
 * Created by lanjing on 2018/12/10.
 */

import properties.TreeNode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * _______3______
 * /              \
 * ___5__          ___1__
 * /      \        /      \
 * 6      _2       0       8
 * /  \
 * 7   4
 * Example 1:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself
 * according to the LCA definition.
 * Note:
 * <p>
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the binary tree.
 */

/**
 * 要求：求一棵二叉树的两个节点的最近公共节点
 * <p>
 * 思路：首先要先确定给的两个node是否都在tree里，如果都在tree里的话，就可以分成3种情况，第一种情况是两个节点是在公共祖先的左右两侧，
 * 第二种情况是都在树的左侧，第三种情况是都在树的右侧，如果是第二，第三种情况的话，公共祖先就在给定的两个点中比较上面的那一个。
 * <p>
 * 如果转换成代码的话，从上往下走，base case分为3种，判断遇到了p就直接返回p，遇到q就直接返回q，不用向下做了。如果left,right都不为空，
 * 就返回root自己；left,right哪一个不为空就返回哪个，整个recursion做完就可以得到LCA。
 */
public class _236LowestCommonAncestorofaBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }
        if (root == p) {
            return p;
        }
        if (root == q) {
            return q;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (null == left && null == right) {
            return root;
        }
        return left != null ? left : right;
    }

}
