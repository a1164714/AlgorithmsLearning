package com.delightintl.demo.sort;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + (r - l) / 2;// l +((r - l)>>1)
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, r);
    }

    private static void merge(int[] arr, int l, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int mid = l + (r - l) / 2;
        int lIndex = l;
        int rIndex = mid + 1;
        while (lIndex <= mid && rIndex <= r) {
            help[i++] = arr[lIndex] < arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
        }
        while (lIndex <= mid) {
            help[i++] = arr[lIndex++];
        }
        while (rIndex <= r) {
            help[i++] = arr[rIndex++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l++] = help[j];
        }
    }


    public static void sort(int[] arr) {
        Arrays.sort(arr);
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 2, 5, 6, 23, 35, 1, 24};
        int[] cpArr = Arrays.copyOf(arr, arr.length);
        mergeSort(arr);
        printArr(arr);
        //##################
        sort(cpArr);
        printArr(cpArr);
    }
}
