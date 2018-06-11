package com.delightintl.demo.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {
    private Node<T> first;
    private int size = 0;

    public LinkedListStack() {
    }

    @Override
    public void push(T t) {
        Node node = new Node(t);
        node.next = first;
        first = node;
        size++;
    }

    @Override
    public T pop() {
        if (empty()) throw new NoSuchElementException("Stack underflow");
        T t = first.t;
        first = first.next;
        size--;
        return t;
    }

    @Override
    public T peek() {
        return first.t;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIteraor<>(first);
    }

    @Override
    public boolean empty() {
        return size == 0;
    }


    private class LinkedListIteraor<T> implements Iterator<T> {
        private Node<T> first;

        public LinkedListIteraor(Node first) {
            this.first = first;
        }

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T t = first.t;
            first = first.next;
            return t;
        }
    }

    private class Node<T> {
        public T t;
        public Node<T> next;

        public Node(T t) {
            this.t = t;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedListStack<>();
        stack.push(8);
        stack.push(3);
        stack.push(2);
        stack.push(10);
        System.out.println(stack.empty());
        System.out.println(stack.size());
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
        }
        System.out.println();
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        Iterator<Integer> iterator2 = stack.iterator();
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + ",");
        }
    }
}
