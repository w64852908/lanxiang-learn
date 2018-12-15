package com.lanxiang.exercise.sort;

import java.util.Arrays;

/**
 * Created by lanjing on 2018/12/7.
 */
public class HandMaxHeap {

    int[] heap;

    int size;

    public HandMaxHeap(int[] heap) {
        this.heap = heap;
        this.size = heap.length;
    }

    //对节点进行下沉操作
    public void maxify(int i) {
        int left = left(i);
        int right = right(i);
        int largest;
        if (left < size && heap[left] > heap[i]) {
            largest = left;
        } else {
            largest = i;
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }
        if (largest == i || largest > size) {
            return;
        }
        int temp = heap[i];
        heap[i] = heap[largest];
        heap[largest] = temp;
        maxify(largest);
    }

    //构建最大堆
    //从最后一个分支节点，分前面每一个值进行下沉操作
    public void buildMaxHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            maxify(i);
        }
    }

    //获取当前节点的左子节点
    private int left(int i) {
        return 2 * i + 1;
    }

    //获取当前节点的右子节点
    private int right(int i) {
        return 2 * i + 2;
    }

    public static void main(String[] args) {
        int[] array = new int[]{46, 79, 56, 38, 40, 84};
        HandMaxHeap head = new HandMaxHeap(array);
        head.buildMaxHeap();
        printArray(head.heap);
        head.heapSort();
        printArray(head.heap);
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void heapSort() {
        for (int i = 0; i < heap.length; i++) {
            int temp = heap[0];
            heap[0] = heap[size - 1];
            heap[size - 1] = temp;
            size--;
            maxify(0);
        }
    }

    public int[] output() {
        return Arrays.copyOf(heap, heap.length);
    }
}
