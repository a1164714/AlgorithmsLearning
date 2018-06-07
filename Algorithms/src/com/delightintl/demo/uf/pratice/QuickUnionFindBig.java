package com.delightintl.demo.uf.pratice;

import com.delightintl.demo.uf.theory.UnionFind;

public class QuickUnionFindBig implements UnionFind {
    private int[] arr;
    private int count;

    public QuickUnionFindBig(int N) {
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
        if (rootP == rootQ)
            return;
        count--;
        if (rootP < rootQ) {
            arr[rootP] = rootQ;
        } else {
            arr[rootQ] = rootP;
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

    public static void main(String[] args) {
        QuickUnionFindBig quickUnion = new QuickUnionFindBig(10);
        quickUnion.union(1, 2);
        quickUnion.union(3, 5);
        quickUnion.union(7, 9);
        quickUnion.union(8, 9);
        quickUnion.union(7, 3);
        System.out.println(quickUnion.find(3)); // return 9
        System.out.println(quickUnion.find(9)); // return 9
    }
}

