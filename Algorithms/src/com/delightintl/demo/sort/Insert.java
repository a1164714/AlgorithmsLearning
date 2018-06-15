package com.delightintl.demo.sort;

import java.util.Arrays;

public class Insert {

    public static void sort(Comparable[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j],arr[j-1])) {
                    exch(arr, j - 1, j);
                }
            }
        }
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static boolean less(Comparable t1, Comparable t2) {
        return t1.compareTo(t2) < 0;
    }


    public static void sort(Integer[] arr) {
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
        Insert.sort(arr);
        printArr(arr);
        //##################
        sort(cpArr);
        printArr(cpArr);
    }
}
