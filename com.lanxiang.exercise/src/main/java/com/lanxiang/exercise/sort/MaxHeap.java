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
        //如果当前节点已经是最大了
        if (largest == i || largest >= heapSize) {
            return;
        }


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
}
