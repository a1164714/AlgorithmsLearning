package com.delightintl.demo.queue;

import java.util.Iterator;

public interface Queue<T> {
    void enqueue(T t);

    int size();

    boolean empty();

    T peek();

    Iterator<T> iterator();

    T dequeue();

}
