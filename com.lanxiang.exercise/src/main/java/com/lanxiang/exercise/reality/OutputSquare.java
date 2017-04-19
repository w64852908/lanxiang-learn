package com.lanxiang.exercise.reality;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/17.
 */


/**
 * 一个正方形,斜着输出s
 */
public class OutputSquare {

    public void output(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int row = 0;
            int column = i;
            while (column >= 0) {
                System.out.print(arr[row][column] + " ");
                row++;
                column--;
            }
            System.out.println();
        }

        for (int i = 1; i <= len - 1; i++) {
            int column = len - 1;
            int row = i;
            while (row <= len - 1) {
                System.out.print(arr[row][column] + " ");
                row++;
                column--;
            }
            System.out.println();
        }
    }

    @Test
    public void run() {
        int[][] a = generateSquare();
        output(a);
    }

    private int[][] generateSquare() {
        int[][] a = new int[4][4];
        int n = 1;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = n++;
            }
        }
        return a;
    }
}
