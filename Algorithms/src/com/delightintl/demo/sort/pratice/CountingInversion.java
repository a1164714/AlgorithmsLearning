package com.delightintl.demo.sort.pratice;

import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.Merge;

import java.util.Arrays;

public class CountingInversion {
    private static boolean less(Comparable t1, Comparable t2) {
        return t1.compareTo(t2) < 0;
    }

    public static void sort(Comparable[] arr) {
        if (arr == null || arr.length < 2)
            return;
        Comparable[] aux = new Comparable[arr.length];
        int count = sort(arr, aux, 0, arr.length - 1);
        System.out.println("Count Inversion :" + count);
    }

    private static int sort(Comparable[] arr, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;
        int countL = sort(arr, aux, lo, mid);
        int countR = sort(arr, aux, mid + 1, hi);
        if (!less(arr[mid + 1], arr[mid])) return 0;
        return countL + countR + merge(arr, aux, lo, mid, hi);
    }

    private static int merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++)
            aux[i] = arr[i];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (less(aux[i], aux[j])) arr[k] = aux[i++];
            else {
                arr[k] = aux[j++];
                count += mid + 1 - k;
            }
        }
        return count;
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
        Integer[] arr = {3, 2, 1};
        Integer[] cpArr = Arrays.copyOf(arr, arr.length);
        sysSort(arr);
        printArr(arr);
        //##################
        sort(cpArr);
        printArr(cpArr);
    }
}
