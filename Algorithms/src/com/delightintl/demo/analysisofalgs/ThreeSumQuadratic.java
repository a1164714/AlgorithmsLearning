package com.delightintl.demo.analysisofalgs;

import com.delightintl.demo.sort.QuickSort;

import java.util.HashMap;
import java.util.Map;

public class ThreeSumQuadratic {
    public static void main(String[] args) {
        int[] arr = {};
        int count = 0;
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++)
            map.put(arr[i], i);
        QuickSort.quickSort(arr);
        for (int j = 0; j < arr.length; j++) {
            for (int k = j + 1; k < arr.length; k++) {
                int thirdVal = -(arr[j] + arr[k]);
                if (map.containsKey(thirdVal) && map.get(thirdVal) != j && map.get(thirdVal) != k) {
                    count++;
                }
            }
        }
        System.out.println("Three sum count:" + count);
    }
}
