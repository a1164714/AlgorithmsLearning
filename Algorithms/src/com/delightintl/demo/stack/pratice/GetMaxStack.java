package com.delightintl.demo.stack.pratice;

import com.delightintl.demo.stack.LinkedListStack;
import com.delightintl.demo.stack.Stack;

import java.util.Iterator;

public class GetMaxStack<T extends Comparable> implements Stack<T> {
    Stack<T> dataStack;
    Stack<T> maxStack;

    public GetMaxStack() {
        dataStack = new LinkedListStack<>();
        maxStack = new LinkedListStack<>();
    }


    @Override
    public void push(T t) {
        dataStack.push(t);
        T peek = maxStack.peek();
        if (t.compareTo(peek) > 0) {
            maxStack.push(t);
        } else {
            dataStack.push(peek);
        }
    }

    public T getMin() {
        return maxStack.peek();
    }

    @Override
    public T pop() {
        maxStack.pop();
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
