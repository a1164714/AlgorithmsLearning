package com.delightintl.demo.uf.pratice;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF weightedQU;
    private boolean[] openArr;
    private final int sideLength;
    private int openNum = 0;
    private final int upGridIndex;
    private final int downGridIndex;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n is Illegal Argument.");
        weightedQU = new WeightedQuickUnionUF(n * n + 2);
        upGridIndex = n * n;
        downGridIndex = n * n + 1;
        openArr = new boolean[n * n];
        for (int i = 0; i < openArr.length; i++)
            openArr[i] = false;
        sideLength = n;
    }

    private int queryIndex(int row, int col) {
        return (row - 1) * sideLength + col - 1;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > sideLength) throw new IndexOutOfBoundsException("row index index out of bounds");
        if (col <= 0 || col > sideLength) throw new IndexOutOfBoundsException("col index index out of bounds");
        if (isOpen(row, col))
            return;
        int index = queryIndex(row, col);
        openNum++;
        openArr[queryIndex(row, col)] = true;
        if (row == 1) {
            weightedQU.union(upGridIndex, index);
        }
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            weightedQU.union(index, queryIndex(row - 1, col));
        }
        if (col + 1 <= sideLength && isOpen(row, col + 1)) {
            weightedQU.union(index, queryIndex(row, col + 1));
        }
        if (row + 1 <= sideLength && isOpen(row + 1, col)) {
            weightedQU.union(index, queryIndex(row + 1, col));
        }
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            weightedQU.union(index, queryIndex(row, col - 1));
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > sideLength) throw new IndexOutOfBoundsException("row index index out of bounds");
        if (col <= 0 || col > sideLength) throw new IndexOutOfBoundsException("col index index out of bounds");
        return openArr[queryIndex(row, col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > sideLength) throw new IndexOutOfBoundsException("row index index out of bounds");
        if (col <= 0 || col > sideLength) throw new IndexOutOfBoundsException("col index index out of bounds");
        boolean connected = weightedQU.connected(upGridIndex, queryIndex(row, col));
        if (connected && row == sideLength) {
            weightedQU.union(downGridIndex, upGridIndex);
        }
        return connected;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openNum;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQU.connected(upGridIndex, downGridIndex);
    }
}

