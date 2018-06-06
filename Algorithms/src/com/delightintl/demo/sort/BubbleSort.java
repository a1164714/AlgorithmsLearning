package com.delightintl.demo.sort;

import java.util.Arrays;

public class BubbleSort {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
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
        bubbleSort(arr);
        printArr(arr);
        //##################
        sort(cpArr);
        printArr(cpArr);
    }

}
