package org.pshiblo.compiler.hash;

import java.util.ArrayList;
import java.util.List;

public class HashTable<T extends Hashable> {

    private final List<HashElement<T>> table;
    private int size;


    public HashTable(int size) {
        table = new ArrayList<>(size);
        this.size = size;
    }

    private int hash(String str) {
        char[] chars = str.toCharArray();
        int sum = 0;
        for (char aChar : chars) {
            sum += (int)aChar;
        }
        return sum % size;
    }


    public T getElement(String key) {

    }

    public void addElement(T value) {

    }
}
