package com.delightintl.demo.sort;

public class Shuffing {

    private static void swan(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void shuffing(Object[] arr) {
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            int random = (int) (Math.random() * i) + 1;
            swan(arr, i, random);
        }
    }
    public static void printArr(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Shuffing.shuffing(arr);
        printArr(arr);
    }
}
