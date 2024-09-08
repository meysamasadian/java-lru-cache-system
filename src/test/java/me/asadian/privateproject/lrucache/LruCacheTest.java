package me.asadian.privateproject.lrucache;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class LruCacheTest extends TestCase {

    private LruCache<Integer, String> cache;

    @Before
    public void setUp() {
        // Initialize the LRU cache with a capacity of 3 for testing
        cache = new LruCacheImpl<>(3);
    }

    @Test
    public void testPutAndGet() {
        // Test inserting and retrieving key-value pairs
        cache.put(1, "one");
        cache.put(2, "two");

        assertEquals("one", cache.get(1));
        assertEquals("two", cache.get(2));
    }

    @Test
    public void testUpdateExistingKey() {
        // Test updating a value for an existing key
        cache.put(1, "one");
        cache.put(1, "newOne");

        assertEquals("newOne", cache.get(1));
    }

    @Test
    public void testEvictionPolicy() {
        // Test the LRU eviction policy
        cache = new LruCacheImpl<>(2); // Set cache capacity to 2

        cache.put(1, "one");
        cache.put(2, "two");
        cache.get(1); // Access key 1 to mark it as recently used
        cache.put(3, "three"); // This should evict key 2 (least recently used)

        assertEquals("one", cache.get(1));
        assertNull(cache.get(2)); // Key 2 should be evicted
        assertEquals("three", cache.get(3));
    }

    @Test
    public void testEvictionWhenCapacityIsReached() {
        // Test eviction when the cache size reaches capacity
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four"); // This should evict key 1 (LRU)

        assertNull(cache.get(1)); // Key 1 should be evicted
        assertEquals("two", cache.get(2));
        assertEquals("three", cache.get(3));
        assertEquals("four", cache.get(4));
    }

    @Test
    public void testRemoveExistingKey() {
        // Test removing an existing key
        cache.put(1, "one");
        cache.put(2, "two");

        cache.remove(1); // Remove key 1

        assertNull(cache.get(1)); // Key 1 should be removed
        assertEquals("two", cache.get(2)); // Key 2 should still be present
    }

    @Test
    public void testRemoveNonExistentKey() {
        // Test removing a non-existent key
        cache.put(1, "one");

        cache.remove(3); // Try to remove key 3, which doesn't exist

        assertEquals("one", cache.get(1)); // Key 1 should still be present
    }
}