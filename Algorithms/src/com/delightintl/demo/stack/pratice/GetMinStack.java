package com.delightintl.demo.stack.pratice;

import com.delightintl.demo.stack.LinkedListStack;
import com.delightintl.demo.stack.Stack;

import java.util.Comparator;
import java.util.Iterator;

public class GetMinStack<T extends Comparable> implements Stack<T> {
    Stack<T> dataStack;
    Stack<T> minStack;

    public GetMinStack() {
        dataStack = new LinkedListStack<>();
        minStack = new LinkedListStack<>();
    }


    @Override
    public void push(T t) {
        dataStack.push(t);
        T peek = minStack.peek();
        if (t.compareTo(peek) < 0) {
            minStack.push(t);
        } else {
            dataStack.push(peek);
        }
    }

    public T getMin() {
        return minStack.peek();
    }

    @Override
    public T pop() {
        minStack.pop();
        return dataStack.pop();
    }

    @Override
    public T peek() {
        return dataStack.peek();
    }

    @Override
    public int size() {
        return dataStack.size();
    }

    @Override
    public Iterator<T> iterator() {
        return dataStack.iterator();
    }

    @Override
    public boolean empty() {
        return dataStack.empty();
    }
}
