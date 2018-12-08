package code;

/**
 * Created by lanjing on 2018/12/8.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * <p>
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * <p>
 * Example:
 * <p>
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */

/**
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * <p>
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * <p>
 * Example:
 * <p>
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * <p>
 * 要求：有A、B、C、D四个数组，要求找到每个数组找一个元素然后和为0的情况数量
 * <p>
 * 思路：和two sum思路有点像，用map保存分组的情况，首先两层循环遍历A和B数组所有和的情况，如果重复的话，value会记录
 * 然后遍历C和D数组所有和的情况，如果和的相反值在map中存在，说明匹配上了。但是注意这个时候，计算res的时候，是每匹配上一次，要加上map里
 * value的值，而不是单纯的++
 */
public class _454_4SumII {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (null == A || null == B || null == C || null == D) {
            return 0;
        }
        int res = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                if (!countMap.containsKey(sum)) {
                    countMap.put(sum, 1);
                } else {
                    int times = countMap.get(sum);
                    countMap.put(sum, ++times);
                }
            }
        }

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                if (countMap.containsKey(-sum)) {
                    res += countMap.get(-sum);
                }
            }
        }
        return res;
    }
}
