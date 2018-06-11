package com.delightintl.demo.queue;

import com.delightintl.demo.stack.ArrayStack;
import com.delightintl.demo.stack.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {
    private T[] arr;
    private int head;
    private int tail;
    private int size;

    public ArrayQueue() {
        this(1);
    }

    public ArrayQueue(int N) {
        arr = (T[]) new Object[N];
        int head = 0;
        int tail = 0;
        int size = 0;
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

    @Override
    public void enqueue(T t) {
        if (size == arr.length) {
            throw new RuntimeException("queue is full.");
        }
        if (size >= arr.length * 3 / 4) {
            resize(arr.length * 2);
        }
        arr[head++] = t;
        head = head == arr.length ? 0 : head;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public T peek() {
        return arr[tail];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(arr, tail, size);
    }

    private class ArrayIterator<T> implements Iterator<T> {
        private T[] arr;
        private int tail;
        private int size;

        public ArrayIterator(T[] arr, int tail, int size) {
            this.arr = arr;
            this.tail = tail;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new RuntimeException("queue is full.");
            }
            T t = arr[tail++];
            tail = tail == arr.length ? 0 : tail;
            size--;
            return t;
        }
    }

    @Override
    public T dequeue() {
        if (size <= 0) {
            throw new NoSuchElementException();
        }
        if (size <= arr.length / 4) {
            resize(arr.length / 2);
        }
        T t = arr[tail++];
        head = head == arr.length ? 0 : head;
        size--;
        return t;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(8);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(10);
        System.out.println(queue.empty());
        System.out.println(queue.size());
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
        }
        System.out.println();
        System.out.println(queue.peek());
        System.out.println(queue.dequeue());
        Iterator<Integer> iterator2 = queue.iterator();
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + ",");
        }
        System.out.println();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
