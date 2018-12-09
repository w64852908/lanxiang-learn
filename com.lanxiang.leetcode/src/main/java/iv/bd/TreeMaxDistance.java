package iv.bd;

/**
 * Created by lanjing on 2018/12/9.
 */

import org.junit.Test;
import properties.TreeNode;

/**
 * 一个二叉树的最远的两个节点距离
 */
public class TreeMaxDistance {

    private int maxDistance = 0;

    public int getMaxDistance(TreeNode root) {
        if (null == root) {
            return 0;
        }
        recursive(root);
        return maxDistance;
    }

    public int recursive(TreeNode p) {
        //空节点的高度返回-1
        if (null == p) {
            return -1;
        }
        int leftHeight = recursive(p.left) + 1;
        int rightHeight = recursive(p.right) + 1;
        int distance = leftHeight + rightHeight;
        maxDistance = Math.max(distance, maxDistance);
        return Math.max(leftHeight, rightHeight);
    }

    @Test
    public void run() {
        TreeNode root = new TreeNode(1);
        TreeNode p1 = new TreeNode(2);
        TreeNode p2 = new TreeNode(3);
        TreeNode p3 = new TreeNode(4);
        TreeNode p4 = new TreeNode(5);
        TreeNode p5 = new TreeNode(7);
        root.left = p1;
        root.right = p2;
        p1.left = p3;
        p1.right = p4;
        p2.right = p5;

        System.out.println(getMaxDistance(root));
    }
}
