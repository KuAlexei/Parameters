package com.epam.ld.javabasics2_1.parameters;

import java.util.Comparator;

public interface IMyList<T extends Number>{

    int getSize();

    T get();
    T get(int index);

    void add(T obj);
    void add(int index, T obj);

    void sort();
    void sort(Comparator<? super T> comparator);

    T remove();
    T remove(int index);

}
