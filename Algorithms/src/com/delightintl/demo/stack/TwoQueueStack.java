package com.delightintl.demo.stack;


import com.delightintl.demo.queue.LinkedListQueue;
import com.delightintl.demo.queue.Queue;

import java.util.IllegalFormatCodePointException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoQueueStack<T> implements Stack<T> {
    private Queue<T> inQueue;
    private Queue<T> outQueue;
    private int size;

    public TwoQueueStack() {
        inQueue = new LinkedListQueue<>();
        outQueue = new LinkedListQueue<>();
        size = 0;
    }

    @Override
    public void push(T t) {
        size++;
        inQueue.enqueue(t);
    }

    @Override
    public T pop() {
        if (outQueue.empty() && inQueue.empty())
            throw new NoSuchElementException();
        size--;
        if (inQueue.size() == 1)
            return inQueue.dequeue();
        return dequeueByTwoQueue();
    }

    private T dequeueByTwoQueue() {
        while (inQueue.size() > 1) {
            outQueue.enqueue(inQueue.dequeue());
        }
        T t = inQueue.dequeue();
        Queue<T> tmp = inQueue;
        inQueue = outQueue;
        outQueue = tmp;
        return t;
    }

    private T peekByTwoQueue() {
        while (inQueue.size() > 1) {
            outQueue.enqueue(inQueue.dequeue());
        }
        T t = inQueue.peek();
        outQueue.enqueue(inQueue.dequeue());
        Queue<T> tmp = inQueue;
        inQueue = outQueue;
        outQueue = tmp;
        return t;
    }

    @Override
    public T peek() {
        if (inQueue.size() == 1)
            return inQueue.peek();
        return peekByTwoQueue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new TwoQueueStack<>();
        stack.push(8);
        stack.push(3);
        stack.push(2);
        stack.push(10);
        System.out.println(stack.empty());
        System.out.println(stack.size());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
