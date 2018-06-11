package com.delightintl.demo.queue;

import com.delightintl.demo.stack.LinkedListStack;
import com.delightintl.demo.stack.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoStackQueue<T> implements Queue<T> {
    private Stack<T> inStack;
    private Stack<T> outStack;
    private int size;

    public TwoStackQueue() {
        inStack = new LinkedListStack<>();
        outStack = new LinkedListStack<>();
        size = 0;
    }

    @Override
    public void enqueue(T t) {
        inStack.push(t);
    }

    @Override
    public T dequeue() {
        if (outStack.empty() && inStack.empty())
            throw new NoSuchElementException();
        if (!outStack.empty())
            return outStack.pop();
        else
            twoStackDequeue();
        return outStack.pop();
    }

    private void twoStackDequeue() {
        while (!inStack.empty()) {
            outStack.push(inStack.pop());
        }
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
        if (!outStack.empty())
            return outStack.peek();
        else
            twoStackDequeue();
        return outStack.peek();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(8);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(10);
        System.out.println(queue.empty());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

}
