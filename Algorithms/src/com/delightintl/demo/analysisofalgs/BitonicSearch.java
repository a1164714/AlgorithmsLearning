package com.delightintl.demo.analysisofalgs;

public class BitonicSearch {
    public static int bitonicSearch(int[] arr, int target) {
        return bitonicSearch(arr, 0, arr.length - 1, target);
    }

    private static int bitonicSearch(int[] arr, int L, int R, int target) {
        if (L >= R)
            return -1;
        int mid = L + ((R - L) >> 1);
        int r = mid + 1;
        int l = mid - 1;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            if (arr[mid] < arr[l] && arr[mid] > arr[r]) {
                int i = binarySearch(arr, mid, R, target);
                if (i == -1) {
                    return bitonicSearch(arr, L, mid, target);
                } else {
                    return i;
                }
            }
            if (arr[mid] > arr[l] && arr[mid] < arr[r]) {
                int i = binarySearch(arr, L, mid, target);
                if (i == -1) {
                    return bitonicSearch(arr, mid, R, target);
                } else {
                    return i;
                }
            }
            // mid 位于顶点
            if (arr[mid] > arr[l] && arr[mid] > arr[r]) {
                int i = binarySearch(arr, L, mid, target);
                if (i == -1) {
                    return binarySearch(arr, mid, R, target);
                } else {
                    return i;
                }
            }
        } else {
            // 右峰
            if (arr[mid] < arr[l] && arr[mid] > arr[r]) {
                return bitonicSearch(arr, L, mid, target);
            }
            // 左峰
            if (arr[mid] > arr[l] && arr[mid] < arr[r]) {
                return bitonicSearch(arr, mid, R, target);
            }
        }
        return -1;
    }


    public static int binarySearch(int[] a, int L, int R, int key) {
        int lo = L, hi = R;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 8, 10, 11, 18, 17, 6, 4, 3, 2, 1};
        int i = bitonicSearch(arr, 18);
        System.out.println(i+":"+arr[i]);
    }
}
