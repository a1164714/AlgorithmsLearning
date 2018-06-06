package com.delightintl.demo.uf.theory;

public class QuickFind implements UnionFind {
    private int[] arr;
    private int count;

    public QuickFind(int N) {
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }
        count = N;
    }

    public void union(int p, int q) {
        if (!connected(p, q)) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == arr[p]) {
                    arr[i] = q;
                }
            }
            count--;
        }
    }

    public boolean connected(int p, int q) {
        if (p >= arr.length || q >= arr.length) {
            throw new RuntimeException("p or q is bigger than N-1");
        }
        if (arr[p] == arr[q]) {
            return true;
        }
        return false;
    }

    @Override
    public int find(int p) {
        return arr[p];
    }

    public int count() {
        return count;
    }


}
