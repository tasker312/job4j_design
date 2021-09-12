package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        skipEmpty();
        return row != data.length;
    }

    private void skipEmpty() {
        for (int i = row; i < data.length; i++) {
            if (data[i].length != 0) {
                row = i;
                return;
            }
        }
        row = data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rowLen = data[row].length - 1;
        if (column == rowLen) {
            column = 0;
            return data[row++][rowLen];
        }
        return data[row][column++];
    }
}
