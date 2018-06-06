package com.delightintl.demo.uf.theory;

public class QuickUnion implements UnionFind {
    private int[] arr;
    private int count;

    public QuickUnion(int N) {
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }
        count = N;
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            arr[rootP] = rootQ;
            count--;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    // 查找p所在的并集合
    public int find(int p) {
        while (arr[p] != p) {
            p = arr[p];
        }
        return p;
    }

    @Override
    public int count() {
        return count;
    }
}
