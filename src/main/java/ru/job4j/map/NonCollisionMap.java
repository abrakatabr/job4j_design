package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((count * 100 / capacity) >= LOAD_FACTOR * 100) {
            expand();
        }
        boolean result = false;
        int i = index(key);
        if (table[i] == null) {
            table[i] = new MapEntry<K, V>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity *= 2;
        for (MapEntry<K, V> mapEntry : table) {
            if (mapEntry != null) {
                int i = index(mapEntry.key);
                newTable[i] = mapEntry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int i = index(key);
        if (table[i] != null) {
            if (isEqual(key, table[i].key)) {
                result = table[i].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int i = index(key);
        if (table[i] != null) {
            if (isEqual(key, table[i].key)) {
                table[i] = null;
                count--;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    private int index(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean isEqual(K key1, K key2) {
        return Objects.hashCode(key1) == Objects.hashCode(key2)
                && Objects.equals(key1, key2);
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, Integer> map = new NonCollisionMap<>();
        System.out.println(map.hash(0));
        System.out.println(map.hash(65535));
        System.out.println(map.hash(65536));
        System.out.println(map.indexFor(0));
        System.out.println(map.indexFor(7));
        System.out.println(map.indexFor(8));
    }
}
