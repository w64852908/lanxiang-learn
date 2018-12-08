package com.lanxiang.exercise.sort;

/**
 * Created by lanjing on 2017/3/29.
 */
public class MaxHeap {

    int[] heap;

    int heapSize;

    public MaxHeap(int[] arr) {
        this.heap = arr;
        this.heapSize = arr.length;
    }

    //构建最大堆
    public void buildMaxHeap() {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            //依次向上将当前子树最大堆化
            maxify(i);
        }
    }

    //移动节点组成最大堆
    public void maxify(int i) {
        int left = left(i);
        int right = right(i);
        int largest;
        //当前节点小于该节点的左子节点
        if (left < heapSize && heap[left] > heap[i]) {
            //左子节点值是最大的
            largest = left;
        } else {
            largest = i;
        }
        //最大的节点小于右子节点
        if (right < heapSize && heap[right] > heap[largest]) {
            largest = right;
        }
        //如果largest等于i说明i是最大元素 largest超出heap范围说明不存在比i节点大的子女
        if (largest == i || largest > heapSize) {
            return;
        }
        //交换i与largest对应的元素位置，在largest位置递归调用maxify
        int tmp = heap[i];
        heap[i] = heap[largest];
        heap[largest] = tmp;
        maxify(largest);
    }

    //获取当前节点的父节点
    private int parent(int i) {
        return (i - 1) / 2;
    }

    //获取当前节点的左子节点
    private int left(int i) {
        return 2 * i + 1;
    }

    //获取当前节点的右子节点
    private int right(int i) {
        return 2 * i + 2;
    }

    public void HeapSort() {
        for (int i = 0; i < heap.length; i++) {
            //执行n次，将每个当前最大的值放到堆末尾
            int tmp = heap[0];
            heap[0] = heap[heapSize - 1];
            heap[heapSize - 1] = tmp;
            heapSize--;
            maxify(0);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{46, 79, 56, 38, 40, 84};
        MaxHeap heap = new MaxHeap(array);
        System.out.println("执行最大堆化前堆的结构：");
        printArray(heap.heap);
        heap.buildMaxHeap();
        System.out.println("执行最大堆化后堆的结构：");
        printHeapTree(heap.heap);
        System.out.println();
        printArray(heap.heap);
        heap.HeapSort();
        System.out.println("执行堆排序后数组的内容");
        printArray(heap.heap);
    }

    private static void printHeapTree(int[] array) {
        for (int i = 1; i < array.length; i = i * 2) {
            for (int k = i - 1; k < 2 * (i) - 1 && k < array.length; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.println();
        }
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
