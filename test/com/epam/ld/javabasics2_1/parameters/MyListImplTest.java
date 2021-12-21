package com.epam.ld.javabasics2_1.parameters;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MyListImplTest {

    @Test
    void remove() {
        IMyList<Integer> myList = new MyListImpl<>();
        assertThrows(IndexOutOfBoundsException.class, () -> myList.remove());
        IntStream.rangeClosed(0, 25).boxed().forEach(myList::add);
        assertEquals(25, assertDoesNotThrow(() -> myList.remove()));
        assertEquals(24, assertDoesNotThrow(() -> myList.remove(24)));
        assertEquals(5, assertDoesNotThrow(() -> myList.remove(5)));
        assertEquals(0, assertDoesNotThrow(() -> myList.remove(0)));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.remove(30));
    }

    @Test
    void get() {
        IMyList<Integer> myList = new MyListImpl<>();
        assertThrows(IndexOutOfBoundsException.class, () -> myList.get());
        IntStream.rangeClosed(0, 25).boxed().forEach(myList::add);
        assertEquals(25, assertDoesNotThrow(() -> myList.get()));
        assertEquals(5, assertDoesNotThrow(() -> myList.get(5)));
        assertEquals(0, assertDoesNotThrow(() -> myList.get(0)));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(30));
    }

    @Test
    void add() {
        IMyList<Integer> myList = new MyListImpl<>();
        assertThrows(IndexOutOfBoundsException.class, () -> myList.add(1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.add(-1, 1));
        assertDoesNotThrow(() -> myList.add(1));
        IntStream.rangeClosed(2, 10).boxed().forEach(myList::add);
        assertDoesNotThrow(() -> myList.add(0, 0));
        IntStream.rangeClosed(12, 19).boxed().forEach(myList::add);
        assertDoesNotThrow(() -> myList.add(11, 11));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.add(30, 30));
        assertThrows(IllegalArgumentException.class, () -> myList.add(null));
        assertThrows(IllegalArgumentException.class, () -> myList.add(1, null));
    }

    @Test
    void sort() {
        IMyList<Integer> myList = new MyListImpl<>();
        assertDoesNotThrow(() -> myList.sort());
        ThreadLocalRandom.current().ints(0, 100).limit(25).boxed().forEach(myList::add);
        assertDoesNotThrow(() -> myList.sort());
    }

}