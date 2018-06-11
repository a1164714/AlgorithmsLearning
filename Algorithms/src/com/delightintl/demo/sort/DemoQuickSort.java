package com.delightintl.demo.sort;

public class DemoQuickSort {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException();
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int L, int R) {
        if (L >= R)
            return;
        int diff = (int) (Math.random() * (R - L));
        swap(arr, L + diff, R);
        int[] eqArr = partition(arr, L, R);
        quickSort(arr, L, eqArr[0] - 1);
        quickSort(arr, eqArr[1] + 1, R);
    }

    private static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[R] > arr[L]) {
                swap(arr, ++less, L++);
            } else if (arr[R] < arr[L]) {
                swap(arr, L, --more);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 10, 77, 51, 23, 51, 20, 75};
        quickSort(arr);
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + ",");
        System.out.println();
    }
}
