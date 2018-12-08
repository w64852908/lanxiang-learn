package code;

import properties.TreeNode;

import java.util.*;

/**
 * Created by lanjing on 2018/12/6.
 */

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * <p>
 * 要求：给一棵二叉树， 输出从根节点往右看能看到的情况（输出每一层的最右节点）
 * <p>
 * 思路：用队列存储遍历二叉树层次遍历情况，用null做每层节点的分割
 */
public class _199BinayTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        boolean isLast;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            //如果当前节点为层次分割节点，继续遍历
            if (null == node) {
                continue;
            }
            isLast = false;
            //如果下一个节点为null，当前节点就是这一层的最后一个节点
            if (queue.peek() == null) {
                res.add(node.val);
                isLast = true;
            }
            if (null != node.left) {
                queue.offer(node.left);
            }
            if (null != node.right) {
                queue.offer(node.right);
            }
            //本层最后一个节点，在分层的地方入队null
            if (isLast) {
                queue.offer(null);
            }
        }
        return res;
    }

}
