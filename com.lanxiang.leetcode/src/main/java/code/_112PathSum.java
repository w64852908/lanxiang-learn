package code;

import org.junit.Test;
import properties.TreeNode;

import java.util.Stack;

/**
 * Created by lanjing on 2017/4/10.
 */

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree and sum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 *
 * 题意:找到二叉树的路径的和是否有和目标值相等的
 *
 * 思路:找所有根节点到叶子节点的path,二叉树的前序遍历
 */
public class _112PathSum {

    //维护一个统计当前值的变量
    private Integer curr = 0;

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        //记录所有节点的值
        Stack<Integer> val = new Stack<>();
        return jugde(root, sum, val);
    }

    private boolean jugde(TreeNode p, int sum, Stack<Integer> val) {
        if (p == null) {
            return false;
        }
        curr += p.val;
        val.push(p.val);
        //如果一个节点没有左子树和右子树,那么判断一次
        if (p.left == null && p.right == null) {
            if (curr.equals(sum)) {
                return true;
            }
        }
        boolean left = false;
        if (p.left != null) {
            left = jugde(p.left, sum, val);
            curr -= val.pop();
        }
        boolean right = false;
        if (p.right != null) {
            right = jugde(p.right, sum, val);
            curr -= val.pop();
        }
        return left || right;
    }


    @Test
    public void run() {
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(8);
        TreeNode n4 = new TreeNode(11);
        TreeNode n5 = new TreeNode(13);
        TreeNode n6 = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(2);
        TreeNode n9 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.left = n5;
        n3.right = n6;
        n4.left = n7;
        n4.right = n8;
        n6.right = n9;

        System.out.println(hasPathSum(n1, 22));
    }
}
