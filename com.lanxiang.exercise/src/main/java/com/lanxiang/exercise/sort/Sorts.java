package com.lanxiang.exercise.sort;

import com.lanxiang.exercise.util.ArrayOutputUtil;
import org.junit.Test;

/**
 * Created by lanjing on 2018/12/7.
 */
public class Sorts {

    public void insertSort(int[] a) {
        if (null == a || a.length == 0) {
            return;
        }
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (temp > a[j]) {
                    break;
                } else {
                    a[j + 1] = a[j];
                }
            }
            a[j + 1] = temp;
        }
    }

    public void shellSort(int[] a) {

    }

    public void selectSort(int[] a) {
        if (null == a || a.length == 0) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            int min = a[i];
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (min > a[j]) {
                    min = a[j];
                    minIndex = j;
                }
            }
            int temp = a[i];
            a[i] = min;
            a[minIndex] = temp;
        }
    }

    public void quickSort(int[] a, int left, int right) {
        if (null == a || left < 0 || right > a.length - 1 || left >= right) {
            return;
        }
        int mid = divide(a, left, right);
        quickSort(a, left, mid - 1);
        quickSort(a, mid + 1, right);
    }

    private int divide(int[] a, int left, int right) {
        int temp = a[left];
        while (left < right) {
            while (left < right && a[right] >= temp) {
                right--;
            }
            a[left] = a[right];
            while (left < right && a[left] <= temp) {
                left++;
            }
            a[right] = a[left];
        }
        a[left] = temp;
        return left;
    }

    @Test
    public void sort() {
        int[] arr = {4, 5, 1, 2, 8, 6, 7, 3, 10, 9};
//        insertSort(arr);
//        selectSort(arr);
//        quickSort(arr, 0, arr.length - 1);
        HandMaxHeap heap = new HandMaxHeap(arr);
        heap.buildMaxHeap();
        heap.heapSort();
        ArrayOutputUtil.printArray(heap.output());
    }
}