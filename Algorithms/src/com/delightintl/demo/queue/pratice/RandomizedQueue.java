package com.delightintl.demo.queue.pratice;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;

    public RandomizedQueue() {
        arr = (Item[]) new Object[2];
        size = 0;
    }

    private void resize(int newLength) {
        Item[] newArr = (Item[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == arr.length) {
            resize(arr.length * 2);
        }
        arr[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size <= arr.length / 4) {
            resize(arr.length / 2);
        }
        int uniform = StdRandom.uniform(size);
        Item item = arr[uniform];
        arr[uniform] = arr[size - 1];
        arr[--size] = null;
        return item;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int uniform = StdRandom.uniform(size);
        return arr[uniform];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator(size);
    }

    private class ArrayIterator<Item> implements Iterator<Item> {
        private int index;
        private int[] list;

        public ArrayIterator(int size) {
            index = size;
            list = new int[size];
            for (int i = 0; i < index; i++) {
                list[i] = i;
            }
            StdRandom.shuffle(list);
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int i = list[index--];
            return (Item) arr[i];
        }
    }

}
