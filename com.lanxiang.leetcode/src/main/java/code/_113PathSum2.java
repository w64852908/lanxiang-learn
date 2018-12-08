package code;

import org.junit.Test;
import properties.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2017/4/19.
 * <p>
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree and sum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * return
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * <p>
 * 要求:获取所有二叉树的路径和与目标值相等的路径,解法与112一致
 */
public class _113PathSum2 {

    private int curr;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        recursive(root, sum, new ArrayList<Integer>(), res);
        return res;
    }

    private void recursive(TreeNode p, int sum, List<Integer> temp, List<List<Integer>> res) {
        if (null == p) {
            return;
        }
        curr = curr + p.val;
        temp.add(p.val);
        if (null == p.left && null == p.right) {
            if (sum == curr) {
                res.add(new ArrayList<>(temp));
            }
        }
        if (p.left != null) {
            recursive(p.left, sum, temp, res);
            curr = curr - temp.get(temp.size() - 1);
            temp.remove(temp.size() - 1);
        }
        if (p.right != null) {
            recursive(p.right, sum, temp, res);
            curr = curr - temp.get(temp.size() - 1);
            temp.remove(temp.size() - 1);
        }
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
        TreeNode n9 = new TreeNode(5);
        TreeNode n10 = new TreeNode(1);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.left = n5;
        n3.right = n6;
        n4.left = n7;
        n4.right = n8;
        n6.left = n9;
        n6.right = n10;

        System.out.println(pathSum(n1, 22));
    }
}
