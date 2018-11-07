package code;

import properties.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by lanjing on 2018/10/31.
 */
public class _145BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> output = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                output.push(p);
                stack.push(p);
                p = p.right;
            } else {
                p = stack.pop();
                p = p.left;
            }
        }
        while (output.size() > 0) {
            result.add(output.pop().val);
        }
        return result;
    }

}
