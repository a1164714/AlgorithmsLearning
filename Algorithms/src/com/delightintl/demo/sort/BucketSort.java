package com.delightintl.demo.sort;

import com.delightintl.demo.queue.Queue;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class BucketSort {
    public static void printArr(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static boolean less(Comparable t1, Comparable t2) {
        return t1.compareTo(t2) < 0;
    }


    public static void bucketSort(Integer[] arr) {
        if (arr == null && arr.length < 2)
            return;
        // 寻找数据区间
        Integer min = arr[0];
        Integer max = arr[0];
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            if (less(arr[i], min))
                min = arr[i];
            if (less(max, arr[i]))
                max = arr[i];
        }
        // 装桶
        int[] buckets = new int[max - min + 1];
        for (int i = 0; i < N; i++) {
            int index = arr[i] - min;
            buckets[index]++;
        }
        // 倒桶
        int bucketSize = buckets.length;
        int j = 0;
        for (int i = 0; i < bucketSize; i++) {
            while (buckets[i]-- > 0) {
                arr[j++] = i + min;
            }
        }
    }

    public static void sort(Integer[] arr) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        Integer[] arr = {0, 1, 55, 4, 5, 2, 5, 6, 23, 35, 1, 24};
        Integer[] cpArr = Arrays.copyOf(arr, arr.length);
        bucketSort(arr);
        printArr(arr);
        //##################
        sort(cpArr);
        printArr(cpArr);
    }
}
