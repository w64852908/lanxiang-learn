package iv.ant;

import iv.util.ArrayUtil;
import org.junit.Test;

/**
 * Created by lanjing on 2018/12/2.
 */

/**
 * 要求：合并两个有序数组
 * <p>
 * 思路：比较，然后合并。小心处理剩余数组的问题。
 */
public class MergeTwoSortArray {


    public int[] merge(int[] arr1, int[] arr2) {
        if (null == arr1 || arr1.length == 0) {
            return arr2;
        }
        if (null == arr2 || arr2.length == 0) {
            return arr1;
        }
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[] res = new int[len1 + len2];
        //l是新数组的下标
        int l1 = 0, l2 = 0, l = 0;
        while (l1 <= len1 - 1 && l2 <= len2 - 1) {
            if (arr1[l1] < arr2[l2]) {
                res[l] = arr1[l1];
                l1++;
            } else {
                res[l] = arr2[l2];
                l2++;
            }
            l++;
        }
        //更长的那个数组
        int[] left = l1 < len1 ? arr1 : arr2;
        //长数组现在的下标
        int i = l1 < len1 ? l1 : l2;
        //遍历长数组剩余的数，同时移动长数组和新数组的下标
        for (; i < left.length; i++, l++) {
            res[l] = left[i];
        }
        return res;
    }

    @Test
    public void run() {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 8, 10, 11, 12};
        int[] merge = merge(arr1, arr2);
        ArrayUtil.display(merge);
    }
}
