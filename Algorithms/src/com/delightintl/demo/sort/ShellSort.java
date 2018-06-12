package com.delightintl.demo.sort;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class ShellSort {
    private static void swan(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static boolean less(Comparable t1, Comparable t2) {
        return t1.compareTo(t2) < 0;
    }

    public static void shellSort(Comparable[] arr) {
        int h = 1;
        int N = arr.length;
        while (h < N / 3)
            h = h * 3 + 1;
        while (h > 0) {
            for (int i = 1; i < N; i++) {
               /* for (int j = i; j > 0; j = j - h) {
                    if (j - h >= 0 && less(arr[j], arr[j - h]))
                        swan(arr, j - h, j);
                }*/
                // because j>0 && j -h >=0 && h > 0   =>  j >= h >0   =>  j >= h
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h)
                    swan(arr, j - h, j);
            }
            // h = (h - 1) / 3; // changeto h = h / 3  => because (h-1)/3 = h/3 + 1/3 = h/3 +0 = h/3;
            h = h / 3;
        }
    }

    public static void printArr(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void sort(Integer[] arr) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        Integer[] arr = {0, 1, 55, 4, 5, 2, 5, 6, 23, 35, 1, 24};
        Integer[] cpArr = Arrays.copyOf(arr, arr.length);
        shellSort(arr);
        printArr(arr);
        //##################
        sort(cpArr);
        printArr(cpArr);
    }
}
