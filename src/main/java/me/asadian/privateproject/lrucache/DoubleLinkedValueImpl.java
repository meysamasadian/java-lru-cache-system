package me.asadian.privateproject.lrucache;

public class DoubleLinkedValueImpl<K,V> implements DoubleLinkedValue<K,V> {

    private final K key;
    private final V value;
    private DoubleLinkedValue<K,V> next;
    private DoubleLinkedValue<K,V> previous;

    public DoubleLinkedValueImpl(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public DoubleLinkedValueImpl() {
        this(null, null);
    }

    @Override
    public DoubleLinkedValue<K,V> next() {
        return next;
    }

    @Override
    public DoubleLinkedValue<K,V> previous() {
        return previous;
    }

    @Override
    public void setNext(DoubleLinkedValue<K,V> next) {
        this.next = next;
    }

    @Override
    public void setPrevious(DoubleLinkedValue<K,V> previous) {
        this.previous = previous;
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public V value() {
        return value;
    }

}
