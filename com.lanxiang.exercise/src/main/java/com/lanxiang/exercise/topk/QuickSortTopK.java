package com.lanxiang.exercise.topk;

import com.lanxiang.exercise.util.ArrayOutputUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lanjing on 2018/11/24.
 */
public class QuickSortTopK {

    public int[] getTopK(int[] nums, int k) {
        if (k > nums.length) {
            return null;
        }
        int low = 0, high = nums.length - 1;
        int mid = divide(nums, low, high);
        //划分位置不是K则继续处理
        while (mid != k) {
            //k在分划点后面部分
            if (k > mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
            //K在分划点前面部分
            mid = divide(nums, low, high);
        }
        return Arrays.copyOf(nums, k);
    }

    private int divide(int[] nums, int low, int high) {
        //数组的第一个数作为中轴
        int temp = nums[low];
        while (low < high) {
            //从右到左,找到第一个比中轴小的数
            while (low < high && nums[high] >= temp) {
                high--;
            }
            //比中轴小的数移到左边
            nums[low] = nums[high];
            //从左到右,找到第一个比中轴大的数
            while (low < high && nums[low] <= temp) {
                low++;
            }
            //比中轴大的数移到右边
            nums[high] = nums[low];
        }
        //位置移动完一轮后,记录中轴最后的值
        nums[low] = temp;
        return low;
    }

    @Test
    public void run() {
        int[] a = {5, 2, 8, 9, 2, 3, 4, 9};
        int[] res = getTopK(a, 3);
        for (int n : res) {
            System.out.print(n + " ");
        }
    }
}
