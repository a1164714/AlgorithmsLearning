package com.delightintl.demo.queue.pratice;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] arr;
    private int tail;
    private int head;
    private int size;

    public ArrayDeque() {
        arr = (Item[]) new Object[2];
        tail = 0;
        head = 0;
        size = 0;
    }

    private void resize(int newLength) {
        Item[] newArr = (Item[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[tail++];
            tail = tail == this.arr.length ? 0 : tail;
        }
        head = size;
        tail = 0;
        arr = newArr;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == arr.length)
            resize(arr.length * 2);
        tail = --tail == -1 ? arr.length - 1 : tail;
        arr[tail] = item;
        size++;
    }

    @Override
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size >= arr.length * 3 / 4)
            resize(arr.length * 2);
        arr[head++] = item;
        head = head == arr.length ? 0 : head;
        size++;
    }

    @Override
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size <= arr.length / 4) {
            resize(arr.length / 2);
        }
        Item item = arr[tail];
        arr[tail] = null;
        tail = ++tail == arr.length ? 0 : tail;
        size--;
        return item;
    }

    @Override
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        head = --head == -1 ? arr.length - 1 : head;
        Item item = arr[head];
        arr[head] = null;
        if (size <= arr.length / 4)
            resize(arr.length / 2);
        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator<Item>(tail);
    }

    private class ArrayIterator<T> implements Iterator<T> {
        private int current;

        public ArrayIterator(int current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return arr[current] != null;
        }

        @Override
        public T next() {
            T t = (T) arr[current++];
            current = current == arr.length ? 0 : current;
            return t;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addLast(3);
        deque.addFirst(2);
        deque.addLast(5);
        Iterator<Integer> iterator1 = deque.iterator();
        while (iterator1.hasNext()) {
            StdOut.print(iterator1.next() + " ");
        }
        StdOut.println();
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            StdOut.print(iterator.next() + " ");
        }
        StdOut.println();
    }
}
