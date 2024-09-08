package me.asadian.privateproject.lrucache;


public class DoubleLinkedListImpl<K, V> implements DoubleLinkedList<K, V> {

    private final DoubleLinkedValue<K, V> head;
    private final DoubleLinkedValue<K, V> tail;

    public DoubleLinkedListImpl() {
        head = new DoubleLinkedValueImpl<>();
        tail = new DoubleLinkedValueImpl<>();

        head.setNext(tail);
        tail.setPrevious(head);
    }


    @Override
    public void addFist(DoubleLinkedValue<K, V> newValue) {
        var oldNext = head.next();
        head.setNext(newValue);
        newValue.setPrevious(head);

        newValue.setNext(oldNext);
        oldNext.setPrevious(newValue);
    }

    @Override
    public void moveAhead(DoubleLinkedValue<K, V> currentValue) {
        var next = currentValue.next();
        var previous = currentValue.previous();

        next.setPrevious(previous);
        previous.setNext(next);

        addFist(currentValue);
    }

    @Override
    public K removeLast() {
        if (tail.previous() != head) {
            var removedValue = tail.previous();
            return remove(removedValue);
        }

        return null;
    }

    @Override
    public K remove(DoubleLinkedValue<K, V> removedValue) {
        var next = removedValue.next();
        var previous = removedValue.previous();

        previous.setNext(next);
        next.setPrevious(previous);

        return removedValue.key();
    }
}
