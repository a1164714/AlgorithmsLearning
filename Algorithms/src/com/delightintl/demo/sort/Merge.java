package com.delightintl.demo.sort;

import edu.princeton.cs.algs4.Heap;

import java.util.Arrays;

public class Merge {
    private static void exch(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static boolean less(Comparable t1, Comparable t2) {
        return t1.compareTo(t2) < 0;
    }

    public static void sort(Comparable[] arr) {
        if (arr == null || arr.length < 2)
            return;
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = lo + ((hi - lo) >> 1);
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
//        if (!less(arr[mid + 1], arr[mid])) return;
        merge(arr, aux, lo, mid, hi);
    }



    private static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              arr[k] = aux[j++];
            else if (j > hi)               arr[k] = aux[i++];
            else if (less(aux[j], aux[i])) arr[k] = aux[j++];
            else                           arr[k] = aux[i++];
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
        Integer[] arr = {1, 55, 0, 4, 5, 2, 5, 6, 23, 35, 1, 24};
        Integer[] cpArr = Arrays.copyOf(arr, arr.length);
        sort(arr);
        printArr(arr);
        //##################
        sysSort(cpArr);
        printArr(cpArr);
    }
}
