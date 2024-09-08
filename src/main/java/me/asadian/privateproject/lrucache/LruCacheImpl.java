package me.asadian.privateproject.lrucache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LruCacheImpl<K, V> implements LruCache<K, V> {

    private final int maxSize;
    private final Map<K, DoubleLinkedValue<K, V>> cacheData;
    private final DoubleLinkedList<K, V> cacheIndex;
    private final AtomicInteger currentSize;

    public LruCacheImpl(int maxSize) {
        this.maxSize = maxSize;
        cacheData = new HashMap<>(maxSize);
        currentSize = new AtomicInteger(0);
        cacheIndex = new DoubleLinkedListImpl<>();
    }

    @Override
    public synchronized void put(K key, V value) {
        if (alreadyExists(key)) {
            remove(key);
            insertNewValue(key, value);
        } else {
            if (cacheIsFull()) {
                cleanUpLeastRecentlyUsed();
            }
            insertNewValue(key, value);
        }
    }

    private void cleanUpLeastRecentlyUsed() {
        var leastRecentlyUsedKey = cacheIndex.removeLast();
        cacheData.remove(leastRecentlyUsedKey);
        currentSize.decrementAndGet();
    }

    private boolean cacheIsFull() {
        return currentSize.get() == maxSize;
    }


    @Override
    public synchronized V get(K key) {
        if (alreadyExists(key)) {
            updateMostRecentlyUsed(key);
            return cacheData.get(key).value();
        }

        return null;
    }

    @Override
    public synchronized void remove(K key) {
        if (alreadyExists(key)) {
            cacheIndex.remove(cacheData.remove(key));
            currentSize.decrementAndGet();
        }
    }

    @Override
    public synchronized int size() {
        return currentSize.get();
    }

    private void insertNewValue(K key, V value) {
        var doubleLinkedValue = new DoubleLinkedValueImpl<>(key, value);
        cacheData.put(key, doubleLinkedValue);
        cacheIndex.addFist(doubleLinkedValue);
        currentSize.incrementAndGet();
    }

    private void updateMostRecentlyUsed(K key) {
        cacheIndex.moveAhead(cacheData.get(key));
    }

    private boolean alreadyExists(K key) {
        return cacheData.containsKey(key);
    }
}
