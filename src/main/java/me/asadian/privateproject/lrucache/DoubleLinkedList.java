package me.asadian.privateproject.lrucache;

public interface DoubleLinkedList<K, V> {
    void addFist(DoubleLinkedValue<K, V> value);
    void moveAhead(DoubleLinkedValue<K, V> value);
    K removeLast();
    K remove(DoubleLinkedValue<K, V> value);
}
