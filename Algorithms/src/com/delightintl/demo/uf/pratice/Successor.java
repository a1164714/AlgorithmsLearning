package com.delightintl.demo.uf.pratice;

public class Successor {
    private QuickUnionFindBig quickUnionFindBig;
    private boolean[] removedArr;

    public Successor(int N) {
        quickUnionFindBig = new QuickUnionFindBig(N);
        removedArr = new boolean[N];
        for (int i = 0; i < removedArr.length; i++)
            removedArr[i] = false;
    }

    public int findSuccessor(int x) {
        int y = quickUnionFindBig.find(x) + 1;
        return y < removedArr.length ? y : -1;
    }

    public void remove(int x) {
        if (removedArr[x] == true)
            return;
        removedArr[x] = true;
        if (x + 1 < removedArr.length && removedArr[x + 1] == true) {
            quickUnionFindBig.union(x, x + 1);
        }
        if (x - 1 > 0 && removedArr[x - 1] == true) {
            quickUnionFindBig.union(x - 1, x);
        }
    }

    public static void main(String[] args) {
        Successor successor = new Successor(10);
        successor.remove(2);
        System.out.println(successor.findSuccessor(2)); // 3
        successor.remove(4);
        System.out.println(successor.findSuccessor(2)); // 3
        successor.remove(3);
        System.out.println(successor.findSuccessor(2)); // 5
    }
}
