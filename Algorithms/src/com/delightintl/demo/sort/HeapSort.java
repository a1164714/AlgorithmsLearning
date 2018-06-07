package com.delightintl.demo.sort;

import java.util.Arrays;

public class HeapSort {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        heapInsert(arr);
        int size = arr.length - 1;
        swap(arr, 0, size--);
        while (size > 0) {
            heapify(arr, size);
            swap(arr, 0, size--);
        }
    }

    public static void heapInsert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int currentIndex = i;
            while ((currentIndex - 1) / 2 >= 0) {
                if (arr[currentIndex] > arr[(currentIndex - 1) / 2]) {
                    swap(arr, currentIndex, (currentIndex - 1) / 2);
                    currentIndex = (currentIndex - 1) / 2;
                } else {
                    break;
                }
            }
        }
    }

    private static void heapify(int[] arr, int size) {
        for (int i = 0; i * 2 + 1 <= size; ) {
            int largest = arr[i] < arr[i * 2 + 1] ? i * 2 + 1 : i;
            largest = i * 2 + 2 <= size && arr[largest] < arr[i * 2 + 2] ? i * 2 + 2 : largest;
            if (largest == i) {
                break;
            }
            swap(arr, largest, i);
            i = largest;
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
        int[] arr = {1, 4, 5, 2, 5, 6};
        int[] cpArr = Arrays.copyOf(arr, arr.length);
        heapSort(arr);
        printArr(arr);
        //##################
        sort(cpArr);
        printArr(cpArr);
    }
}
