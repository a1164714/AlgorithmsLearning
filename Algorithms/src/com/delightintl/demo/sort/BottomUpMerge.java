package com.delightintl.demo.sort;

import java.util.Arrays;

public class BottomUpMerge {
    private static void exch(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static boolean less(Comparable t1, Comparable t2) {
        return t1.compareTo(t2) < 0;
    }

    private static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++)
            aux[i] = arr[i];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (less(aux[i], aux[j])) arr[k] = aux[i++];
            else arr[k] = aux[j++];
        }
    }

    public static void sort(Comparable[] arr) {
        if (arr == null || arr.length < 2)
            return;
        Comparable[] aux = new Comparable[arr.length];
        int n = arr.length;
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo + len < n; lo = lo + 2 * len) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + 2 * len - 1, n - 1);
                merge(arr, aux, lo, mid, hi);
            }
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
        Integer[] arr = {0, 1, 55, 4, 5, 2, 5, 6, 23, 35, 1, 24};
        Integer[] cpArr = Arrays.copyOf(arr, arr.length);
        BottomUpMerge.sort(arr);
        printArr(arr);
        //##################
        sysSort(cpArr);
        printArr(cpArr);
    }
}
