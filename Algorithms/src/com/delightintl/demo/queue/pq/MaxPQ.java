package com.delightintl.demo.queue.pq;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MaxPQ<Key> {
    private int size;
    private Key[] arr;
    private Comparator comparator;

    public MaxPQ() {
        size = 0;
        arr = (Key[]) new Object[2];
    }

    public MaxPQ(Comparator<Key> comparator) {
        this.comparator = comparator;
        arr = (Key[]) new Object[2];
        size = 0;
    }

    // create a priority queue with given keys
    MaxPQ(Key[] a) {
        size = a.length;
        arr = (Key[]) new Object[a.length + 1];
        for (int i = 0; i < a.length; i++)
            arr[i + 1] = a[i];
        for (int i = size / 2; i >= 1; i--)
            sink(i);
    }

    private void swim(int k) {
        while (k / 2 > 0 && less(arr[k / 2], arr[k])) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(arr[j], arr[j + 1]))
                j++;
            if (less(arr[j], arr[k])) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int k, int j) {
        Key tmp = arr[k];
        arr[k] = arr[j];
        arr[j] = tmp;
    }

    private boolean less(Key key1, Key key2) {
        if (comparator != null)
            return comparator.compare(key1, key2) < 0;
        return ((Comparable) key1).compareTo(key2) < 0;
    }

    // insert a key into the priority queue
    void insert(Key v) {
        if (size == arr.length - 1)
            resize(arr.length * 2);
        arr[++size] = v;
        swim(size);
    }

    //  return and remove the largest key
    Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        exch(1, size);
        Key max = arr[size];
        arr[size--] = null;
        sink(1);
        if (size > 0 && size == (arr.length - 1) / 4)
            resize(arr.length * 2);
        return max;
    }

    // is the priority queue empty?
    boolean isEmpty() {
        return size == 0;
    }

    //    return the largest key
    Key max() {
        if (isEmpty())
            throw new NoSuchElementException();
        return arr[1];
    }

    //    number of entries in the priority queue
    int size() {
        return size;
    }


    private void resize(int newLength) {
        Key[] newArr = (Key[]) new Object[newLength];
        for (int i = 1; i <= size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }
}
