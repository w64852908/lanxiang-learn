package com.lanxiang.exercise.sort;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/16.
 */
public class BubbleSort {

    public void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }

    @Test
    public void run() {
        int[] arr = {2, 4, 9, 3, 6, 7, 1, 5};
        bubbleSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
