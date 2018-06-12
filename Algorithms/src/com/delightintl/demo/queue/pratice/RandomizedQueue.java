package com.delightintl.demo.queue.pratice;

import com.delightintl.demo.queue.ArrayQueue;
import com.delightintl.demo.queue.Queue;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<T> implements Iterable<T> {
    private T[] arr;
    private int size;
    private int head;
    private int tail;

    public RandomizedQueue() {
        arr = (T[]) new Object[2];
        size = 0;
        head = 0;
        tail = 0;
    }

    private void resize(int newLength) {
        T[] newArr = (T[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[tail++];
            tail = tail == arr.length ? 0 : tail;
        }
        head = size;
        tail = 0;
        arr = newArr;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T t) {
        if (t == null)
            throw new IllegalArgumentException();
        if (size >= arr.length * 3 / 4) {
            resize(arr.length * 2);
        }
        arr[head++] = t;
        head = head == arr.length ? 0 : head;
        size++;
    }

    private void swap(int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size <= arr.length / 4) {
            resize(arr.length / 2);
        }
        int uniform = StdRandom.uniform(size);
        int index = tail + uniform < arr.length ? tail + uniform : tail + uniform - arr.length - 1;
        swap(tail, index);
        T t = arr[tail];
        arr[tail] = null;
        tail = ++tail == arr.length ? 0 : tail;
        size--;
        return t;
    }

    public T sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int uniform = StdRandom.uniform(size);
        int index = tail + uniform < arr.length ? tail + uniform : tail + uniform - arr.length - 1;
        swap(tail, index);
        return arr[tail];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(tail);
    }

    private class ArrayIterator<T> implements Iterator<T> {
        private int tail;

        public ArrayIterator(int tail) {
            this.tail = tail;
        }

        @Override
        public boolean hasNext() {
            return arr[tail] != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new RuntimeException("queue is full.");
            }
            T t = (T) arr[tail++];
            tail = tail == arr.length ? 0 : tail;
            return t;
        }
    }
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(8);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(10);
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        System.out.println(queue.sample());
        System.out.println(queue.sample());
        System.out.println(queue.sample());
        System.out.println(queue.sample());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
