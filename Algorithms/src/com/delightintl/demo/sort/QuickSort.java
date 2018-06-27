package com.delightintl.demo.sort;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSort<T extends Comparator> {
    private  Comparator comparator;

    public QuickSort() {
    }

    public QuickSort(Comparator comparator) {
        this.comparator = comparator;
    }

    public  void quickSort(Comparable[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private  void quickSort(Comparable[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] partition = partition(arr, L, R);
        quickSort(arr, L, partition[0] - 1);
        quickSort(arr, partition[1] + 1, R);
    }

    private  boolean less(Comparable t1, Comparable t2) {
        if (comparator != null) {
            return comparator.compare(t1, t2) < 0;
        }
        return t1.compareTo(t2) < 0;
    }

    private  int[] partition(Comparable[] arr, int L, int R) {
        int randomIndex = (int) (Math.random() * (R - L));
        swap(arr, L + randomIndex, R);
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (less(arr[L], arr[R])) {
                swap(arr, ++less, L++);
            } else if (!less(arr[L], arr[R])) {
                swap(arr, L, --more);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 4, 5, 35, 1, 2, 6, 23, 24, 5};
        Integer[] cpArr = Arrays.copyOf(arr, arr.length);
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(arr);
        printArr(arr);
        //##################
        sort(cpArr);
        printArr(cpArr);
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

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
}
