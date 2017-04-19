package code;

/**
 * Created by lanjing on 2017/4/19.
 */

import org.junit.Test;
import properties.ListNode;
import properties.ListNodeUtil;
import properties.TreeNode;
import properties.TreeNodeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * 要求:给一个有序数组,构建一颗二叉搜索树
 * <p>
 * 思路:用二分查找法,中间的数作这一层的根节点
 */
public class _109ConvertSortedListToBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return construct(list, 0, list.size() - 1);
    }

    private TreeNode construct(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = construct(list, left, mid - 1);
        root.right = construct(list, mid + 1, right);
        return root;
    }

    @Test
    public void run() {
        ListNode head = ListNodeUtil.generateList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        TreeNode root = sortedListToBST(head);
        TreeNodeUtil.levelDisplay(root);
    }
}
