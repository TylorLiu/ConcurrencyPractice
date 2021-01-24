package org.tylor.tutorial.algorithm;

import java.util.Arrays;

/**
 * Tylor 2020/12/12
 */
public class KthMax {

}
class Solution {
    public int findKthLargest(int[] arr, int k) {
        int len = arr.length;
        if(k<Math.log(len) / Math.log(2)){
            int index=0,temp;
            for(int i=0;i<k;i++){
                index = i;
                for(int j = i + 1; j < arr.length; j++) {
                    if(arr[j] > arr[index]) {     // 寻找最小的数
                        index = j;                 // 将最小数的索引保存
                    }
                }
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
            return arr[k-1];
        }else{
            Arrays.sort(arr);
            return arr[len-k];
//            int heapSize = arr.length;
//            buildMaxHeap(arr, heapSize);
//            for (int i = arr.length - 1; i >= arr.length - k + 1; --i) {
//                swap(arr, 0, i);
//                --heapSize;
//                maxHeapify(arr, 0, heapSize);
//            }
//            return arr[0];
        }
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

