package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int inputSize = 0;
    private int outputSize = 0;

//    private int size = 0;

//    public T poll() {
//        if (size == 0) {
//            throw new NoSuchElementException("Queue is empty");
//        }
//        for (int i = 0; i < size; i++) {
//            output.push(input.pop());
//        }
//        T value = output.pop();
//        size--;
//        for (int i = 0; i < size; i++) {
//            input.push(output.pop());
//        }
//        return value;
//    }

//    public void push(T value) {
//        input.push(value);
//        size++;
//    }

    public T poll() {
        if (inputSize + outputSize == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outputSize == 0) {
            while (inputSize > 0) {
                output.push(input.pop());
                inputSize--;
                outputSize++;
            }
        }
        outputSize--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        inputSize++;
    }
}
