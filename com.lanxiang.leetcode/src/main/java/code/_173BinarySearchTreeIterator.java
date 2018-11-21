package code;

import org.junit.Test;
import properties.TreeNode;

import java.util.Stack;

/**
 * Created by lanjing on 2018/11/21.
 */

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * <p>
 * 要求：实现一个二叉搜索树迭代器，next方法每次返回最小值，且next和hasNext都要O(1)时间复杂度，最多使用O(h)额外空间。
 * <p>
 * 思路：二叉搜索树从小到大取值的话其实就是中序遍历，用栈来保存所有左子树。hasNext判断栈是否为空，next取值时需要将右子树压栈并遍历所有左子树
 */
public class _173BinarySearchTreeIterator {

    private Stack<TreeNode> stack = new Stack<>();

    public _173BinarySearchTreeIterator(TreeNode root) {
        add(root);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode node = stack.pop();
        add(node.right);
        return node.val;
    }

    private void add(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {

        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        node5.left = node2;
        node5.right = node8;
        node2.left = node1;
        node2.right = node4;
        node8.left = node7;
        node8.right = node9;

        _173BinarySearchTreeIterator searchTreeIterator = new _173BinarySearchTreeIterator(node5);
        while (searchTreeIterator.hasNext()) {
            int value = searchTreeIterator.next();
            System.out.println(value);
        }
    }
}
