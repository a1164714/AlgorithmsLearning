package com.delightintl.demo.stack;

import java.util.Iterator;

public interface Stack<T> {
    void push(T t);

    T pop();

    T peek();

    int size();

    Iterator<T> iterator();

    boolean empty();
}
