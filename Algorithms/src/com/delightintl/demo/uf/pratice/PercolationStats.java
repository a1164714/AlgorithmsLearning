package com.delightintl.demo.uf.pratice;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] xs;
    private int trials;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        xs = new double[trials];
        for (int i = 0; i < xs.length; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                percolation.open(row, col);
                percolation.isFull(row,col);
            }
            double x = (double) percolation.numberOfOpenSites() / (n * n);
            xs[i] = x;
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
        return mean() - (double)1.96 * stddev() / (double)Math.sqrt(trials);
    }


    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (double)1.96 * stddev() /(double) Math.sqrt(trials);
    }


    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + "," + percolationStats.confidenceHi() + "]");

    }
}
