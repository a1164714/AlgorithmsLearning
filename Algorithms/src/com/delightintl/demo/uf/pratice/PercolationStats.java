package com.delightintl.demo.uf.pratice;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] xs;
    private final int trials;
    private static final double CONSTANT_1_96 = 1.96;
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

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
            }
            xs[i] = ((double) percolation.numberOfOpenSites()) / (n * n);
        }
        this.mean = StdStats.mean(xs);
        this.stddev = StdStats.stddev(xs);
        this.confidenceLo = this.mean - CONSTANT_1_96 * this.stddev / Math.sqrt(trials);
        this.confidenceHi = this.mean + CONSTANT_1_96 * this.stddev / Math.sqrt(trials);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }


    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }


    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(new Integer(args[0]).intValue(), new Integer(args[1]).intValue());
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + "," + percolationStats.confidenceHi() + "]");
    }
}
