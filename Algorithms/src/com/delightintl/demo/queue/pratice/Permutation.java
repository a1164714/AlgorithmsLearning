package com.delightintl.demo.queue.pratice;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int n = 1;
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (n <= k) {
                randomizedQueue.enqueue(item);
            } else if (StdRandom.uniform(n) < k) {
                String s = randomizedQueue.dequeue();
                randomizedQueue.enqueue(s);
            }
            n++;
        }
        for (String s : randomizedQueue) {
            StdOut.println(s);
        }
    }
}
