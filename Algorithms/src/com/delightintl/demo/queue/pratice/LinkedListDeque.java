package com.delightintl.demo.queue.pratice;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    public LinkedListDeque() {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T t) {
        if (t == null)
            throw new IllegalArgumentException();
        if (isEmpty()) {
            first = new Node(null, null, t);
            last = first;
        } else {
            first.pre = new Node(null, first, t);
            first = first.pre;
        }
        size++;
    }

    public void addLast(T t) {
        if (t == null)
            throw new IllegalArgumentException();
        if (isEmpty()) {
            last = new Node(null, null, t);
            first = last;
        } else {
            last.next = new Node(last, null, t);
            last = last.next;
        }
        size++;
    }

    public T removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        size--;
        T t = first.t;
        if (last == first)
            last = null;
        if (first.next != null)
            first.next.pre = null;
        first = first.next;
        return t;
    }

    public T removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        size--;
        T t = last.t;
        if (last == first)
            first = null;
        if (last.pre != null)
            last.pre.next = null;
        last = last.pre;
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(first);
    }

    private class LinkedListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public LinkedListIterator(Node first) {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T t = current.t;
            current = current.next;
            return t;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node<T> {
        private Node pre;
        private Node next;
        private T t;

        private Node(Node pre, Node next, T t) {
            this.pre = pre;
            this.next = next;
            this.t = t;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedListDeque<>();
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
