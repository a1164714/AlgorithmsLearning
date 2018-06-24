package com.delightintl.demo.sort;

import java.util.Arrays;

public class Heap {
    private static void exch(Comparable[] arr, int i, int j) {
        Comparable t = arr[i - 1];
        arr[i - 1] = arr[j - 1];
        arr[j - 1] = t;
    }

    private static boolean less(Comparable[] arr, int i, int j) {
        return arr[i - 1].compareTo(arr[j - 1]) < 0;
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = n / 2; i >= 1; i--)
            sink(arr, i, n);
        while (n > 1) {
            exch(arr, 1, n--);
            sink(arr, 1, n);
        }
    }

    private static void sink(Comparable[] arr, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(arr, j, j + 1)) j++;
            if (less(arr, j, k)) break;
            exch(arr, k, j);
            k = j;
        }
    }

    public static void sysSort(Integer[] arr) {
        Arrays.sort(arr);
    }

    public static void printArr(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] arr = {4, 1, 0, 55, 5, 6, 23, 5, 2, 35, 1, 24};
        Integer[] cpArr = Arrays.copyOf(arr, arr.length);
        Heap.sort(arr);
        printArr(arr);
        //##################
        sort(cpArr);
        printArr(cpArr);
    }
}
