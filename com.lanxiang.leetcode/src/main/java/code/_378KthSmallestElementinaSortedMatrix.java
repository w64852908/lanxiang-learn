package code;

/**
 * Created by lanjing on 2018/12/15.
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * Example:
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 * <p>
 * <p>
 * 要求；给一个行和列有序的二维数组，返回数组中第k小的数
 * <p>
 * 思路：构建一个最大堆
 */
public class _378KthSmallestElementinaSortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0 || k <= 0) {
            return -1;
        }
        Queue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -1 * o1.compareTo(o2);
            }
        });
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if ((i + 1) * (j + 1) > k) {
                    break;
                }
                maxHeap.offer(matrix[i][j]);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        return maxHeap.peek();
    }
}
