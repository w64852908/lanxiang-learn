package code;

/**
 * Created by lanjing on 2018/10/17.
 */

import org.junit.Test;
import properties.TreeNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3]
 * 1
 * / \
 * 2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 * <p>
 * Input: [4,9,0,5,1]
 * 4
 * / \
 * 9   0
 * / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 * <p>
 * <p>
 * 要求：求所有叶子节点到根节点的和
 * <p>
 * 思路：递归，前序遍历，记录当前节点的path的sum，到叶子节点时就更新把当前记录的sum增加到最后结果去
 */
public class _129SumRootToLeafNumbers {

    private int res = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        preOrderThrough(root, 0);
        return res;
    }

    private void preOrderThrough(TreeNode root, int temp) {
        if (root == null) {
            return;
        }
        //path的sum
        temp = temp * 10 + root.val;
        //符合题意的节点
        if (root.left == null && root.right == null) {
            res += temp;
        }
        if (root.left != null) {
            preOrderThrough(root.left, temp);
        }
        if (root.right != null) {
            preOrderThrough(root.right, temp);
        }
    }

    @Test
    public void run() {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(3);
        root.left = l;
        root.right = r;
        System.out.println(sumNumbers(root));
    }
}
