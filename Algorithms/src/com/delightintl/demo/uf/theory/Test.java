package com.delightintl.demo.uf.theory;

public class Test {
    public static void main(String[] args) {
        // QuickFind Test:
        UnionFind quickFind = new QuickFind(10);
        quickFind.union(1, 2);
        quickFind.union(3, 5);
        quickFind.union(7, 9);
        quickFind.union(8, 9);
        quickFind.union(7, 3);
        System.out.println(quickFind.connected(1, 5));
        System.out.println(quickFind.connected(7, 9));
        System.out.println(quickFind.count());
        System.out.println(quickFind.find(3));

        // QuickUnion Test:
        UnionFind quickUion = new QuickUnion(10);
        quickUion.union(1, 2);
        quickUion.union(3, 5);
        quickUion.union(7, 9);
        quickUion.union(8, 9);
        quickUion.union(7, 3);
        System.out.println(quickUion.connected(1, 5));
        System.out.println(quickUion.connected(7, 9));
        System.out.println(quickUion.count());
        System.out.println(quickUion.find(3));

        // WeightedQU Test:
        UnionFind weightedQU = new WeightedQU(10);
        weightedQU.union(1, 2);
        weightedQU.union(3, 5);
        weightedQU.union(7, 9);
        weightedQU.union(8, 9);
        weightedQU.union(7, 3);
        System.out.println(weightedQU.connected(1, 5));
        System.out.println(weightedQU.connected(7, 9));
        System.out.println(weightedQU.count());
        System.out.println(weightedQU.find(3)); // 这里结果不一样了，因为由加权去判断谁为根了
    }
}
