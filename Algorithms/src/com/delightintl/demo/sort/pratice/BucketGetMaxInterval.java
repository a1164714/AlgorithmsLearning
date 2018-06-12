package com.delightintl.demo.sort.pratice;

import com.delightintl.demo.stack.LinkedListStack;
import com.delightintl.demo.stack.Stack;

import java.util.Iterator;

// 乱序数据求有序后求间隔最大值
public class BucketGetMaxInterval {
    private static boolean less(Comparable t1, Comparable t2) {
        return t1.compareTo(t2) < 0;
    }

    public static Comparable getMaxInterval(Integer[] arr) {
        // 寻找数据区间
        Integer min = arr[0];
        Integer max = arr[0];
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            if (less(arr[i], min))
                min = arr[i];
            if (less(max, arr[i]))
                max = arr[i];
        }
        // 装桶
        MinMaxStack<Integer>[] stacks = new MinMaxStack[arr.length + 1];
        for (int i = 0; i < stacks.length; i++)
            stacks[i] = new MinMaxStack<>();
        for (int i = 0; i < arr.length; i++) {
            int index = N * (arr[i] - min) / (max - min);
            stacks[index].push(arr[i]);
        }
        MinMaxStack minMaxStack = new MinMaxStack();
        Integer leftMax = stacks[0].getMax();
        for (int i = 1; i < stacks.length; i++) {
            if (!stacks[i].empty()) {
                minMaxStack.push(stacks[i].getMin() - leftMax);
                leftMax = stacks[i].getMax();
            }
        }
        return minMaxStack.getMax();
    }

    private static class MinMaxStack<T extends Comparable> implements Stack<T> {
        private Stack<T> stack;
        private T min;
        private T max;

        public MinMaxStack() {
            stack = new LinkedListStack<>();
            min = null;
            max = null;
        }

        @Override
        public void push(T t) {
            stack.push(t);
            getMinAndMax(t);
        }

        private void getMinAndMax(T t) {
            if (min == null)
                min = t;
            else {
                if (t.compareTo(min) < 0)
                    min = t;
            }
            if (max == null)
                max = t;
            else {
                if (t.compareTo(max) > 0)
                    max = t;
            }
        }

        public T getMin() {
            return min;
        }

        public void setMin(T min) {
            this.min = min;
        }

        public T getMax() {
            return max;
        }

        public void setMax(T max) {
            this.max = max;
        }

        @Override
        public T pop() {
            return stack.pop();
        }

        @Override
        public T peek() {
            return stack.peek();
        }

        @Override
        public int size() {
            return stack.size();
        }

        @Override
        public Iterator<T> iterator() {
            return stack.iterator();
        }

        @Override
        public boolean empty() {
            return stack.empty();
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 3, 2, 4, 7, 8, 10, 9};
        Integer maxInterval = (Integer) getMaxInterval(arr);
        System.out.println(maxInterval);
    }
}
