package com.delightintl.demo.stack;

import java.util.Iterator;

public class ArrayStack<T> implements Stack<T> {
    private T[] arr;
    private int size = 0;

    public ArrayStack() {
        this(1);
    }

    private ArrayStack(int N) {
        arr = (T[]) new Object[N];
    }

    @Override
    public void push(T t) {
        if (size >= arr.length * 3 / 4) {
            arr = resize(arr, size, arr.length * 2);
        }
        arr[size++] = t;
    }

    private T[] resize(T[] arr, int size, int resizeLength) {
        T[] resizeArr = (T[]) new Object[resizeLength];
        for (int i = 0; i < size; i++)
            resizeArr[i] = arr[i];
        return resizeArr;
    }

    @Override
    public T pop() {
        if (size <= arr.length / 4) {
            arr =   resize(arr, size, arr.length / 2);
        }
        return arr[--size];
    }

    @Override
    public T peek() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator<T>(arr, size);
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    private class StackIterator<T> implements Iterator<T> {
        private int size;
        private T[] arr;

        public StackIterator(T[] arr, int size) {
            this.size = size;
            this.arr = arr;
        }

        @Override
        public boolean hasNext() {
            return size != 0;
        }

        @Override
        public T next() {
            return arr[--size];
        }
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack();
        stack.push(8);
        stack.push(3);
        stack.push(2);
        stack.push(10);
        System.out.println(stack.empty());
        System.out.println(stack.size());
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+",");
        }
        System.out.println();
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        Iterator<Integer> iterator2 = stack.iterator();
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next()+",");
        }
    }
}
