package com.lanxiang.exercise.array;

import org.junit.Test;

/**
 * Created by lanjing on 2017/3/28.
 */
@SuppressWarnings("ALL")
public class Merge {

    public int[] merge(int[] arr1, int[] arr2) {
        if ((arr1 == null || arr1.length == 0) && (arr2 == null || arr2.length == 0)) {
            return null;
        } else if ((arr1 == null || arr1.length == 0) || (arr2 == null || arr2.length == 0)) {
            if (arr1 == null || arr1.length == 0) {
                return arr2;
            } else {
                return arr1;
            }
        }
        int len1 = arr1.length;
        int len2 = arr2.length;
        int totalLen = len1 + len2;
        int[] arr = new int[totalLen];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < len1 && index2 < len2) {
            if (arr1[index1] > arr2[index2]) {
                arr[index] = arr2[index2];
                index2++;
            } else {
                arr[index] = arr1[index1];
                index1++;
            }
            index++;
        }
        if (index1 < len1) {
            for (int i = index1; i < len1; i++) {
                arr[index] = arr1[i];
                index++;
            }
        } else {
            for (int i = index2; i < len2; i++) {
                arr[index] = arr2[i];
                index++;
            }
        }
        return arr;
    }

    @Test
    public void run() {
        case1();
        case2();
        case3();
        case4();
        case5();
    }

    private void case1() {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8, 10};
        int[] arr = merge(arr1, arr2);
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();

    }

    private void case2() {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = null;
        int[] arr = merge(arr1, arr2);
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();

    }

    private void case3() {
        int[] arr2 = {1, 3, 5, 7};
        int[] arr1 = null;
        int[] arr = merge(arr1, arr2);
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();

    }

    private void case4() {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {};
        int[] arr = merge(arr1, arr2);
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();

    }

    private void case5() {
        int[] arr2 = {1, 3, 5, 7};
        int[] arr1 = {};
        int[] arr = merge(arr1, arr2);
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();

    }
}
