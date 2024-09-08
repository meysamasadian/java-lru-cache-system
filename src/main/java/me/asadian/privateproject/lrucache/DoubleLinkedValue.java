package me.asadian.privateproject.lrucache;


public interface DoubleLinkedValue<K, V> {
    K key();
    V value();
    DoubleLinkedValue<K, V> next();
    DoubleLinkedValue<K, V> previous();
    void setNext(DoubleLinkedValue<K, V> next);
    void setPrevious(DoubleLinkedValue<K, V> previous);
}
