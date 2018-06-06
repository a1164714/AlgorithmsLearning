package com.delightintl.demo.uf.theory;

public class WeightedQU implements UnionFind {
    private int[] arr;
    private int count;
    private int[] weightedArr;

    public WeightedQU(int N) {
        arr = new int[N];
        weightedArr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
            weightedArr[i] = 1;
        }
        count = N;
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            if (weightedArr[rootP] <= weightedArr[rootQ]) {
                arr[rootP] = rootQ;
                weightedArr[rootQ] += weightedArr[rootP];
            } else {
                arr[rootQ] = rootP;
                weightedArr[rootP] += weightedArr[rootQ];
            }
            count--;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        while (arr[p] != p) {
            // arr[p] = arr[arr[p]]; // with path compression
            p = arr[p];
        }
        return p;
    }

    @Override
    public int count() {
        return count;
    }
}
