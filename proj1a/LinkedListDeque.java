public class LinkedListDeque<T> {
    private class Node{
        private T item;
        private Node prev;
        private Node next;

        private Node(T i, Node p, Node n) {
            this.item = i;
            this.prev = p;
            this.next = n;

        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size =0;
    }
    /** Add an item to the front of the deque */
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;

        size += 1;
    }
    
    /** Remove and return the item at the front of the deque */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        
        T first = sentinel.next.item;

        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        size -= 1;

        return first;
    }

    /** Add an item to the back of the deque */
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;

        size += 1;
    }

    /** Removes and return the item at the back of the deque */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T toBeRemoved = sentinel.prev.item;

        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        size -= 1;
        return toBeRemoved;
    }

    /** Get the item at the given index */
    public T get (int index) {
        if (index >= size || index < 0) {
            return null;
        }

        Node got = sentinel.next;

        for (int i = 0; i < index; i++) {
            got = got.next;
        }

        return got.item;
    }

    public int size() {
        return size;
    }

    public T getRecursive (int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node got = sentinel.next;

        return getRecursiveHelper(got, index);
    }

    private T getRecursiveHelper(Node got, int index) {
        if (index == 0) {
            return got.item;
        }

        return getRecursiveHelper(got.next, index - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        Node start = sentinel.next;

        while (start != sentinel) {
            System.out.print(start.item + " ");
            start = start.next;
        }
    }
}
