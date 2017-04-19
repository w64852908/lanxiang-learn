package code;

import org.junit.Test;
import properties.TreeNode;
import properties.TreeNodeUtil;

import java.util.Arrays;

/**
 * Created by lanjing on 2017/4/18.
 */

/**
 * Given an array where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 * <p>
 * 要求:给一个有序数组,构建一颗二叉搜索树
 * <p>
 * 思路:用二分查找法,中间的数作这一层的根节点
 */
public class _108ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = construct(nums, left, mid - 1);
        root.right = construct(nums, mid + 1, right);
        return root;
    }

    @Test
    public void run() {
        int[] arr = {7, 4, 5, 8, 10, 2, 3, 6, 1, 9};
        Arrays.sort(arr);
        TreeNode root = sortedArrayToBST(arr);
        TreeNodeUtil.levelDisplay(root);
    }
}
