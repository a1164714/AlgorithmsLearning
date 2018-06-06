package com.delightintl.demo.sort;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] partition = partition(arr, L, R);
        quickSort(arr, L, partition[0] - 1);
        quickSort(arr, partition[1] + 1, R);
    }

    private static int[] partition(int[] arr, int L, int R) {
        int randomIndex = (int) (Math.random() * (R - L));
        swap(arr, L + randomIndex, R);
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < arr[R]) {
                swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) {
                swap(arr, L, --more);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 35, 1, 2, 6, 23, 24, 5};
        int[] cpArr = Arrays.copyOf(arr, arr.length);
        quickSort(arr);
        printArr(arr);
        //##################
        sort(cpArr);
        printArr(cpArr);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

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


}
