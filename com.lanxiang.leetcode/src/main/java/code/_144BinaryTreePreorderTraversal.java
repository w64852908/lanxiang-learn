package code;

import properties.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by lanjing on 2018/10/30.
 *
 * 要求：二叉树前序遍历
 *
 * 思路：用栈存右子树节点，p变量遍历左子树
 */
public class _144BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || null != p) {
            if (null != p) {
                result.add(p.val);
                if (null != p.right) {
                    stack.push(p.right);
                }
                p = p.left;
            } else {
                p = stack.pop();
            }
        }
        return result;
    }
}
