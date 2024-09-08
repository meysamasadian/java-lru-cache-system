package me.asadian.privateproject.lrucache;

/**
 * Interface representing a basic LRU (Least Recently Used) Cache.
 * This cache allows for efficient insertion, retrieval, and removal of key-value pairs,
 * while maintaining an eviction policy where the least recently accessed items are removed
 * when the cache reaches its capacity.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
public interface LruCache<K, V> {

    /**
     * Inserts a key-value pair into the cache.
     * If the key already exists, updates the value associated with it.
     * If the cache is at capacity, the least recently used entry is evicted
     * to make space for the new entry.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    void put(K key, V value);

    /**
     * Retrieves the value associated with the given key.
     * If the key exists in the cache, it returns the associated value and
     * marks this entry as recently used.
     * If the key does not exist, it returns null.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if the key does not exist
     */
    V get(K key);

    /**
     * Removes the key-value pair associated with the given key from the cache.
     * If the key does not exist, the method does nothing.
     *
     * @param key the key whose mapping is to be removed from the cache
     */
    void remove(K key);

    /**
     * Returns number of item have already added to the cache
     *
     * @return the value associated with the specified key, or null if the key does not exist
     */
    int size();
}
