package properties;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by lanjing on 2017/4/18.
 */
public class TreeNodeUtil {

    public static void levelDisplay(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> curr = new LinkedList<>();
        curr.offer(root);
        Queue<TreeNode> next;
        while (!curr.isEmpty()) {
            next = new LinkedList<>();
            TreeNode p;
            while (!curr.isEmpty()) {
                p = curr.poll();
                System.out.print(p.val + "\t");
                if (p.left != null) {
                    next.offer(p.left);
                }
                if (p.right != null) {
                    next.offer(p.right);
                }
            }
            System.out.println();
            curr = next;
        }
    }
}
