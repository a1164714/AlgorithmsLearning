package com.delightintl.demo.uf.pratice;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF weightedQU;
    private boolean[] openArr;
    private int sideLength;
    private int openNum = 0;
    private int upGridIndex;
    private int downGridIndex;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new RuntimeException("n must be bigger than zero");
        weightedQU = new WeightedQuickUnionUF(n * n + 2);
        upGridIndex = n * n;
        downGridIndex = n * n + 1;
        openArr = new boolean[n * n];
        for (int i = 0; i < openArr.length; i++)
            openArr[i] = false;
        sideLength = n;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > sideLength) throw new IndexOutOfBoundsException("row index index out of bounds");
        if (col <= 0 || col > sideLength) throw new IndexOutOfBoundsException("col index index out of bounds");
        int index = (row - 1) * sideLength + col - 1;
        if (!isOpen(row, col)) {
            openNum++;
            openArr[index] = true;
            if (row == 1) {
                weightedQU.union(upGridIndex, index);
            } else if (row == sideLength) {
                weightedQU.union(downGridIndex, index);
            }
            if (row - 1 > 0 && isOpen(row - 1, col)) {
                int index2 = (row - 2) * sideLength + col - 1;
                weightedQU.union(index, index2);
            }
            if (row + 1 < sideLength && isOpen(row + 1, col)) {
                int index2 = row * sideLength + col - 1;
                weightedQU.union(index, index2);
            }
            if (col - 1 > 0 && isOpen(row, col - 1)) {
                int index2 = (row - 1) * sideLength + col - 2;
                weightedQU.union(index, index2);
            }
            if (col + 1 < sideLength  && isOpen(row, col + 1)) {
                int index2 = (row - 1) * sideLength + col;
                weightedQU.union(index, index2);
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > sideLength) throw new IndexOutOfBoundsException("row index index out of bounds");
        if (col <= 0 || col > sideLength) throw new IndexOutOfBoundsException("col index index out of bounds");
        int index = (row - 1) * sideLength + col - 1;
        return openArr[index];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > sideLength) throw new IndexOutOfBoundsException("row index index out of bounds");
        if (col <= 0 || col > sideLength) throw new IndexOutOfBoundsException("col index index out of bounds");
        if (percolates()) {
            int index = (row - 1) * sideLength + col - 1;
            int rootUpGrid = weightedQU.find(upGridIndex);
            int rootIndex = weightedQU.find(index);
            return rootUpGrid == rootIndex;
        }
        return false;
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

