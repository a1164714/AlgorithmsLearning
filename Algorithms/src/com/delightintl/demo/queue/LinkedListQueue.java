package com.delightintl.demo.queue;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<T> implements Queue<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedListQueue() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node<T> {
        public T t;
        public Node<T> next;

        public Node(T t) {
            this.t = t;
        }
    }

    @Override
    public void enqueue(T t) {
        Node node = new Node(t);
        if (empty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
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
        return first.t;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIteraotr<>(first);
    }

    private class LinkedListIteraotr<T> implements Iterator<T> {
        private Node<T> first;

        public LinkedListIteraotr(Node<T> first) {
            this.first = first;
        }

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T t = first.t;
            first = first.next;
            return t;
        }
    }

    @Override
    public T dequeue() {
        if (empty()) {
            throw new NoSuchElementException();
        }
        T t = first.t;
        first = first.next;
        size--;
        return t;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<>();
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
        System.out.println(queue.dequeue());

    }
}
