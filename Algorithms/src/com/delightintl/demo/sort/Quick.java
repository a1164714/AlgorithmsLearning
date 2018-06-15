package com.delightintl.demo.sort;

import java.util.Arrays;
import java.util.Queue;

public class Quick {
    private static void exch(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static boolean less(Comparable t1, Comparable t2) {
        return t1.compareTo(t2) < 0;
    }

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi)
            return;
        int partition = partition(arr, lo, hi);
        sort(arr, lo, partition - 1);
        sort(arr, partition + 1, hi);
    }


    private static int partition(Comparable[] arr, int lo, int hi) {
        int record = hi;
        while (lo < hi) {
            if (less(arr[lo], arr[record]))
                lo++;
            else
                exch(arr, lo, --hi);
        }
        exch(arr, hi, record);
        return hi;
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
        Integer[] arr = {0, 1, 55, 4, 2, 5, 6, 23, 35, 24};
        Integer[] cpArr = Arrays.copyOf(arr, arr.length);
        Quick.sort(arr);
        printArr(arr);
        //##################
        sysSort(cpArr);
        printArr(cpArr);
    }

}
