package com.delightintl.demo.uf.pratice;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] xs;
    private final int trials;
    private final double CONSTANT_1_96 = 1.96;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException("n or trials is Illegal Argument");
        this.trials = trials;
        xs = new double[trials];
        for (int i = 0; i < xs.length; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                percolation.open(row, col);
                percolation.isFull(row, col);
            }
            xs[i] = ((double) percolation.numberOfOpenSites()) / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(xs);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(xs);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONSTANT_1_96 * stddev() / Math.sqrt(trials);
    }


    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONSTANT_1_96 * stddev() / Math.sqrt(trials);
    }


    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(new Integer(args[0]).intValue(), new Integer(args[1]).intValue());
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + "," + percolationStats.confidenceHi() + "]");
    }
}
