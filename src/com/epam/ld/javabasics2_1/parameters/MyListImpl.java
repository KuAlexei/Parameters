package com.epam.ld.javabasics2_1.parameters;

import java.util.Comparator;
import java.util.function.Supplier;

public class MyListImpl<T extends Number> implements IMyList<T>{

    private Number[] data = {};
    private int capacity = 0;

    private int size;
    private int increment;

    public MyListImpl() {
        increment = 10;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    @Override
    public T get() {
        return get(size-1);
    }

    @Override
    public T get(int index) {
        return checkRangeAndDo(index,() -> (T) data[index]);
    }

    @Override
    public void add(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        // to be sure we have enough room to add
        Number[] newData;
        if (size < capacity) {
            newData = data;
        } else {
            capacity += increment;
            newData = new Number[capacity];
        }

        // move/copy array part [0..size-1]
        System.arraycopy(data, 0, newData, 0, size);
        newData[size] = obj;
        data = newData;
        size++;
    }

    @Override
    public void add(int index, T obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        checkRangeAndDo(index, () -> {
            // to be sure we have enough room to add
            Number[] newData;
            if (size < capacity) {
                newData = data;
            } else {
                capacity += increment;
                newData = new Number[capacity];
            }

            // move/copy array part [index..size-1]
            System.arraycopy(data, index, newData, index + 1, size - index);
            if (data != newData) {
                // move/copy array part [0..index-1]
                System.arraycopy(data, 0, newData, 0, index);
            }

            newData[index] = obj;
            data = newData;
            size++;
            return null;
        });
    }

    @Override
    public void sort() {
        sort((o1, o2) -> Double.valueOf(Math.signum(o1.doubleValue() - o2.doubleValue())).intValue());
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        bubbleSort(comparator);
    }

    private void bubbleSort(Comparator<? super T> comparator) {
        for (int i = size; i > 0; i--) {
            for (int j = 1; j < i; j++) {
                T a = (T) data[j-1];
                T b = (T) data[j];
                if(comparator.compare(a, b)>0) {
                    data[j-1] = b;
                    data[j] = a;
                }
            }
        }
    }

    @Override
    public T remove() {
        return checkRangeAndDo(size-1, () -> {
            Object removed = data[--size];
            data[size] = null;
            return (T) removed;
        });
    }

    @Override
    public T remove(int index) {
        return checkRangeAndDo(index,() -> {
            Object removed = data[index];
            System.arraycopy(data, index + 1, data, index, (--size) - index);
            return (T) removed;
        });
    }

    private T checkRangeAndDo(int index, Supplier<T> action) {
        if ((index >= 0) && (index < size)) {
            return action.get();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

}
