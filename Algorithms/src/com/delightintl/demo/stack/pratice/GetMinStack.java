package com.delightintl.demo.stack.pratice;

import com.delightintl.demo.stack.LinkedListStack;
import com.delightintl.demo.stack.Stack;

import java.util.Comparator;
import java.util.Iterator;

public class GetMinStack<T extends Comparable> implements Stack<T>{
    Stack<T> dataStack;
    Stack<T> minStack;

    public GetMinStack() {
        dataStack = new LinkedListStack<>();
        minStack = new LinkedListStack<>();
    }


    @Override
    public void push(T  t) {
        T peek = minStack.peek();
    }

    @Override
    public T pop() {
        return null;
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
